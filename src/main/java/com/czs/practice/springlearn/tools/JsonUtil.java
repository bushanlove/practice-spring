package com.czs.practice.springlearn.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.czs.practice.springlearn.dto.AreaDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;


public class JsonUtil {


    private static final String UTF8 = "UTF-8";

    private static Set<String> zxs = Sets.newHashSet();

    public static void main(String[] args){
        try{
            getAreaListFromJson();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static{
        zxs.add("北京市");
        zxs.add("天津市");
        zxs.add("重庆市");
        zxs.add("上海市");
    }



    public static void getAreaListFromJson() throws Exception {

        List<AreaDto> list = Lists.newArrayList();
        ClassPathResource resource = new ClassPathResource("json/country_tree.json");
        File file = resource.getFile();
        String jsonStr = FileUtils.readFileToString(file,UTF8);
        JSONObject country =  (JSONObject) JSONObject.parse(jsonStr);
        //System.out.println(country.getString("name"));
        String counrtyCode = country.getString("adcode");
        //获取省级列表
        JSONArray provinceList = country.getJSONArray("children");

        for(int i=0;i<provinceList.size();i++){
            JSONObject province = provinceList.getJSONObject(i);
            String provinceName = province.getString("name");
            list.add(new AreaDto(province.getString("adcode"),provinceName,counrtyCode,province.getString("level")));
            //直辖市做特殊处理
            if(zxs.contains(provinceName)){

                JSONArray districtList = province.getJSONArray("children");
                JSONObject districtOne = districtList.getJSONObject(0);
                String cityCode = districtOne.getString("cityCode");
                String cityName = provinceName;
                list.add(new AreaDto(cityCode,cityName,province.getString("adcode"),"city"));
                for(int k=0;k<districtList.size();k++){
                    JSONObject district = districtList.getJSONObject(k);
                    list.add(new AreaDto(district.getString("adcode"),district.getString("name"),cityCode,district.getString("level")));
                }
            }else{
                JSONArray cityList = province.getJSONArray("children");
                //System.out.println(provinceName);
                if(null != cityList){
                    for(int j=0;j<cityList.size();j++){
                        JSONObject city = cityList.getJSONObject(j);
                        list.add(new AreaDto(city.getString("adcode"),city.getString("name"),province.getString("adcode"),city.getString("level")));
                        //System.out.println(city.getString("name"));
                        JSONArray districtList = city.getJSONArray("children");
                        if(null != districtList){
                            for(int k=0;k<districtList.size();k++){
                                JSONObject district = districtList.getJSONObject(k);
                                list.add(new AreaDto(district.getString("adcode"),district.getString("name"),city.getString("adcode"),district.getString("level")));
                            }
                        }
                    }
                }
            }
        }


        System.out.println("----------------------------");
        for(AreaDto areaDto : list){
//            System.out.println(areaDto.toString());
            System.out.println(areaDto.toStringWithLevel());

        }
        System.out.println("----------------------------");

    }

}
