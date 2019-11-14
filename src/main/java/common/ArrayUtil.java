package common;

import com.google.gson.Gson;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    public static <T> T parseList(String listStr, Type type) {
        return new Gson().fromJson(listStr, type);
    }

    public static <T> T parseArray(String arrayStr, Type type) {
//        List parseResult = parseList(arrayStr, type);
        return new Gson().fromJson(arrayStr, type);
    }

//    public static <T> T parseArray(String arrayStr, Type type) {
//        List parseResult = parseList(arrayStr, type);
//        return listToArray(parseResult);
//    }

    private static <T> T listToArray(List list) {
        List<Integer> sizeList = new ArrayList<>();
        Object temp = list;
        boolean isEmpty = false;
        while (temp instanceof List) {
            sizeList.add(((List) temp).size());
            if (((List) temp).size() == 0) {
                isEmpty = true;
                break;
            }
        }
        Class clazz = isEmpty ? Object.class : temp.getClass();
        int[] sizes = sizeList.stream().mapToInt(i -> i).toArray();
        Object multiArray = Array.newInstance(clazz, sizes);
        copyValue(list, multiArray, sizes);
        return (T) multiArray;
    }

    private static void copyValue(List list, Object array, int[] sizes) {
        for (int i = 0; i < sizes[0]; i++) {
            if (sizes.length == 1) {
                Array.set(array, i, list.get(i));
            } else {
                copyValue((List) list.get(i), Array.get(array, i), Arrays.copyOfRange(sizes, 1, sizes.length));
            }
        }
    }

}
