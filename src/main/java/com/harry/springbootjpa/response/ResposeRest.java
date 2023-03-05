package com.harry.springbootjpa.response;

import java.util.ArrayList;
import java.util.HashMap;


public class ResposeRest {

    private ArrayList<HashMap<String, Object>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, Object>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String type, String code, String data, Integer page) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("code", code);
        map.put("data", data);
        map.put("page", page);
        this.metadata.add(map);
    }
}
