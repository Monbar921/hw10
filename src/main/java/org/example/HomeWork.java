package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу https://codeforces.com/contest/356/problem/A
     */
    @SneakyThrows
    public void championship(InputStream in, OutputStream out) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
            String[] participate = reader.readLine().split(" ");

            int knights = Integer.parseInt(participate[0]);
            int fights = Integer.parseInt(participate[1]);

            for(int i = 1; i <= knights; ++i){
                tree.add(i, i);
            }

            for(int i =0; i < fights; ++i){
                String[] round = reader.readLine().split(" ");
                int startKnight = Integer.parseInt(round[0]);
                int endKnight = Integer.parseInt(round[1]);
                int winnerKnight = Integer.parseInt(round[2]);

                for(int j = startKnight; j <= endKnight; ++j ){
                    if(j != winnerKnight){
                        tree.setValue(j, winnerKnight);
                    }
                }
            }
        }

        Map<Integer, Integer> knights = tree.inOrder();

        if(knights != null){
            AtomicBoolean isNotFirstKnight = new AtomicBoolean(false);
            knights.forEach((k, v) -> {
                try {
                    if (isNotFirstKnight.get()) {
                        out.write(" ".getBytes());
                    }
                    if(k.compareTo(v) == 0){
                        out.write(String.valueOf(0).getBytes());
                    } else {
                        out.write(String.valueOf(v).getBytes());
                    }

                    if (!isNotFirstKnight.get()) {
                        isNotFirstKnight.set(true);
                    }
                }catch (IOException ignored){

                }
            });
        }
    }
}
