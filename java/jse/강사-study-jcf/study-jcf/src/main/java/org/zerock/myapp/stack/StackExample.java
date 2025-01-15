package org.zerock.myapp.stack;


import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Stack;


@Log4j2
// Stack 자료구조를 이용해보자!!!
public class StackExample {

    public static void main(String[] args) {
        log.debug("main({}) invoked.", Arrays.toString(args));

        Stack<Coin> coinBox = new Stack<>();

        coinBox.push(new Coin(500));
        coinBox.push(new Coin(100));
        coinBox.push(new Coin(50));
        coinBox.push(new Coin(10));

        log.info("\t+ coinBox: {}", coinBox);

        log.info("=".repeat(50));

        Coin topCoin = coinBox.peek();
        log.info("\t+ topCoin: {}", topCoin);

        log.info("=".repeat(50));

        Coin coin = coinBox.pop();
        log.info("\t+ coin: {}", coin);
        log.info("\t+ coinBox: {}", coinBox);

        // Traverse with forEach method
        coinBox.forEach(log::info);

    } // main

} // end class

