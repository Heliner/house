package com.mooc.house.userservice1.common;

import java.util.List;

public class ListResponse<T> {
    private List<T> list;
    private Long count;

    public static  <T> ListResponse<T> build(List<T> list,Long count){
        ListResponse listResponse = new ListResponse();
        listResponse.setCount(count);
        listResponse.setList(list);
        return listResponse;
    }
    public  ListResponse(){

    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
