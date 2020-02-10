package by.epam.web.unit6.command.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для реализации постраничного отображения данных из БД
 */
public class Paging {
    public static Map convertListToPage(List list){
        Map map = new HashMap<Integer, List>();
        List pageList;
        int k=1;
        for (int i = 0; i <list.size() ; i++) {
            pageList=new ArrayList();
            for (int j = 0; j <5 ; j++) { //5 записей выводится на экран
                pageList.add(list.get(i));
                if(j==4){
                    map.put(k, pageList);
                    k++;
                }
            }
        }
        return map;
    }
}
