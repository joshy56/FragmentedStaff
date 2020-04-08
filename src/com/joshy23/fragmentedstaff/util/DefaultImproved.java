package com.joshy23.fragmentedstaff.util;

import java.util.LinkedList;
import java.util.List;

public class DefaultImproved {

    public static <E> E getElement(List<E> list, E element){
        if(list instanceof LinkedList){
            for(E e:list){
                if(e.equals(element)){
                    return element;
                }
            }
        }else{
            for(int i = 0, size = list.size(); i < size; i++){
                if(list.get(i).equals(element)){
                    return element;
                }
            }
        }
        return null;
    }
/*
    public void removeAll(List<T> list){
        if(list instanceof LinkedList){
            for(Object<T> element:list){

            }
        }
    }
*/
}
