package org.example;

import entities.*;
import dao.*;
import lombok.SneakyThrows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        connection.getConnection();
        loginIn();

    }

    @SneakyThrows
    private static void loginIn() {
        Scanner sc1 = new Scanner(System.in);
        boolean flag = true;
        int id;
        String idNumber;
        String name;
        String pwd;
        String realName;
        String phoneNumber;
        String address;
        String description;
        boolean sex;
        while (flag) {
            String welcomeLine = "==============================";
            String welcomeWord1 = """
                    欢迎使用饿了么外卖平台
                    用户登录请按1\t商家登录请按2
                    管理员登录请按3
                    用户注册请按4\t商家注册请按5
                    管理员注册请按6
                    退出请按0""";
            System.out.println(welcomeLine);
            System.out.println(welcomeWord1);

            int choice = sc1.nextInt();
            switch (choice) {
                case 0 -> {
                    System.out.println("感谢使用，再见");
                    flag = false;

                }
                case 1 -> {//todo 用户登录

                }
                case 2 -> {//todo 商家登录

                }
                case 3 -> {//todo 管理员登录

                }
                case 4 -> {//todo 用户注册

                }
                case 5 -> {//todo 商家注册

                }
                case 6 -> {//todo 管理员注册


                }
                default -> {
                    System.out.println("输入无效，请重新输入！");
                }

            }
        }
    }
}

/* 　　        ▃▆█▇▄▖
　　     　▟◤▖　　　◥█▎
　　　◢◤　     ▐　　　　▐▉
　▗◤　　　  ▂　▗▖　　▕   █▎
　◤　  ▗▅▖◥▄　▀◣　　  █▊
▐　   ▕▎◥▖◣◤　　　　◢██
█◣　  ◥▅█▀　　　　 ▐██◤
▐█▙▂　　　      ◢██◤
　◥██◣　　　◢▄◤
　　　▀██▅▇▀
太痛苦力

*/
