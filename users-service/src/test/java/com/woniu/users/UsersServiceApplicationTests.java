package com.woniu.users;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

class UsersServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     *  选择排序
     */
    @Test
    void test1(){
        int[] arr = {2,5,7,1,8,9,4,9};
        for(int i = 0; i < arr.length-2; i++){
            int minInx = i;
            for(int j = i +1; j < arr.length-1; j++){
                if(arr[minInx] > arr[j]){
                    minInx = j;
                }
            }
            int tem = arr[minInx];
            arr[minInx] = arr[i];
            arr[i] = tem;
        }

        System.out.println(Arrays.toString(arr));
    }

}
