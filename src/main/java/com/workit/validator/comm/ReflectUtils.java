package com.workit.validator.comm;

import com.workit.validator.annotation.MyNotBlank;
import com.workit.validator.annotation.ParameterValidator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: 
 * @Date: 2020/6/15 
 * @Description:
 */
public final class ReflectUtils {

    private ReflectUtils() {
    }

    static Set<String> TYPE = new HashSet<>();
    static {
        TYPE.add("java.lang.Integer");
        TYPE.add("java.lang.Double");
        TYPE.add("java.lang.Float");
        TYPE.add("java.lang.Long");
        TYPE.add("java.lang.Short");
        TYPE.add("java.lang.Byte");
        TYPE.add("java.lang.Boolean");
        TYPE.add("java.lang.Character");
        TYPE.add("java.lang.String");
    }



    public static boolean isPrimitive(Class<?> clazz) {
        return TYPE.contains(clazz.getTypeName()) || clazz.isPrimitive();

    }

    /**
     * 使 filed 变为可访问
     *
     * @param field
     */
    public static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
    }

    public static void checkField(Object object, Class<?> aClass) throws IllegalAccessException {
        boolean primitive = isPrimitive(aClass);
        if (primitive) {
            return;
        }
        Field[] declaredFields = filterField(aClass.getDeclaredFields());
        for (Field field : declaredFields) {
            makeAccessible(field);
            MyNotBlank fieldAnnotation = field.getAnnotation(MyNotBlank.class);
            Object currentObject = field.get(object);
            if (Objects.nonNull(fieldAnnotation)) {
                if (StringUtils.isEmpty(currentObject)) {
                    throw  new IllegalArgumentException(field.getName()+":"+fieldAnnotation.message());
                }
            }
            if (!isJavaClass(field.getType())) {
                checkField(currentObject);
            } else if (field.getType().isPrimitive()) {

            } else if (field.getType().isAssignableFrom(List.class)) {
                getList(field, currentObject);
            }

        }

    }



    private static void getList(Field field, Object currentObject) {
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            // 获取到属性值的字节码
            try {
                Class<?> clzz = currentObject.getClass();
                // 反射调用获取到list的size方法来获取到集合的大小
                Method sizeMethod = clzz.getDeclaredMethod("size");
                if (!sizeMethod.isAccessible()) {
                    sizeMethod.setAccessible(true);
                }
                // 集合长度
                int size = (int) sizeMethod.invoke(currentObject);
                // 循环遍历获取到数据
                for (int i = 0; i < size; i++) {
                    // 反射获取到list的get方法
                    Method getMethod = clzz.getDeclaredMethod("get", int.class);
                    // 调用get方法获取数据
                    if (!getMethod.isAccessible()) {
                        getMethod.setAccessible(true);
                    }
                    Object invoke = getMethod.invoke(currentObject, i);
                    if (isPrimitive(invoke.getClass())) {
                        continue;
                    }
                    checkField(invoke, invoke.getClass());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 过滤字段
     * 
     * @param
     * @return
     */
    public static Field[] filterField(Field[] fields) {
        List<Field> tempList = Arrays.stream(fields).filter(field -> null != field && !Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())
                && !Modifier.isAbstract(field.getModifiers())).collect(Collectors.toList());
        int arrLength = CollectionUtils.isEmpty(tempList) ? 1 : tempList.size();
        Field[] resultArr = new Field[arrLength];
        if (!CollectionUtils.isEmpty(tempList)) {
            tempList.toArray(resultArr);
        }
        return resultArr;
    }

    public static void checkField(Object o) throws IllegalAccessException {
        Class<?> aClass = o.getClass();
        checkField(o, aClass);

    }

    public static boolean check(String type) {
        return type.contains("com.workit");
    }

    private static HashMap<String, Object> getMemberFields(HashMap<String, Object> fieldValues, Object obj) throws IllegalAccessException, NoSuchFieldException {
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldValues.put(field.getName(), field.get(obj));
            if (!field.getType().isPrimitive() && !field.getType().getName().contains("java.lang")) {
                if (Objects.nonNull(field.get(obj))) {
                    getMemberFields(fieldValues, field.get(obj));
                }
            }

        }
        return fieldValues;
    }

    public static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }

}
