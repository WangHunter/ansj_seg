package org.ansj.demo;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.stopWord.GetStopWordList;
import org.nlpcn.commons.lang.util.IOUtil;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/9.
 */
public class test {
    public static void main(String[] args) throws Exception {
        GetStopWordList getStopWordList = new GetStopWordList();
        Map<String, List> map = getStopWordList.getStopWordList();
        List<String> list_c = map.get("MacroDef.STOP_CHINESE");
        List<String> list_e = map.get("MacroDef.STOP_ENGLISH");


        KeyWordComputer kwc = new KeyWordComputer(10);
        String tt=new String();
        BufferedReader reader = IOUtil.getReader("D:\\Desktop\\李开复.txt", "utf-8");
        String str;
        while ((str = reader.readLine()) != null) {
            tt+=str;
        }
        String title = "李开复：无人驾驶进入黄金时代 AI有巨大投资机会";
        String content = tt;
//        Collection<Keyword> result = kwc.computeArticleTfidf(title, content);
//        System.out.println(result);

        Iterator it = kwc.computeArticleTfidf(title,content).iterator();

        while(it.hasNext()) {
            boolean flag = true;
            Keyword key2=(Keyword)it.next();
            for (String str_c : list_c) {
                if (str_c.equals(key2.getName().toString()))
                    flag = false;
            }
            for (String str_e : list_e) {
                if (str_e.equals(key2.getName().toString()))
                    flag = false;
            }
            if(flag) {
                System.out.println(key2.getName().toString()+key2.getScore());
            }
        }
    }
}
