package puzzels.jaar2022;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Puzzel11 {
    public enum EnumOperation {
        ENUM_VERMENIGVULDIGER,
        ENUM_OPTELLER,
        ENUM_KWADRAAT
    }

    private static class Monkey {
        private List<Integer> items = new ArrayList<>();
        private EnumOperation eo;
        private int operationValue;
        private int testDeler;
        private int testTrue;
        private int testFalse;

        public List<Integer> getItems() {
            return items;
        }

        public void setItems(List<Integer> items) {
            this.items = items;
        }

        public EnumOperation getEo() {
            return eo;
        }

        public void setEo(EnumOperation eo) {
            this.eo = eo;
        }

        public int getOperationValue() {
            return operationValue;
        }

        public void setOperationValue(int operationValue) {
            this.operationValue = operationValue;
        }

        public int getTestDeler() {
            return testDeler;
        }

        public void setTestDeler(int testDeler) {
            this.testDeler = testDeler;
        }

        public int getTestTrue() {
            return testTrue;
        }

        public void setTestTrue(int testTrue) {
            this.testTrue = testTrue;
        }

        public int getTestFalse() {
            return testFalse;
        }

        public void setTestFalse(int testFalse) {
            this.testFalse = testFalse;
        }

        public void addItem(Integer item) {
            this.items.add(item);
        }

        public void print() {
            System.out.println("Monkey:");
            System.out.printf("\tStarting items: ");
            for (int v :
                    getItems()) {
                System.out.printf("%d ", v);
            }
            System.out.println();
            System.out.printf("\tOperations: %s %d\n", getEo(), getOperationValue());
            System.out.printf("\tTest: divided by %d\n", getTestDeler());
            System.out.printf("\tTest: true %d\n", getTestTrue());
            System.out.printf("\tTest: false %d\n", getTestFalse());
        }

        public List<Integer> removeItems() {
            List<Integer> clonedItems = new ArrayList<>(items);
            items.clear();
            return clonedItems;
        }
    }

    public void start() throws Exception {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/input11a.txt";
        File file = new File(fileName);

//        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            Monkey[] monkeys = new Monkey[4];
            int index = -1;

            String line;
            while ((line = br.readLine()) != null) {
                int len = line.length();
                String[] str = line.trim().split(" ");
                String cmd = str[0];

                switch (cmd) {
                    case "Monkey":
                        index = Integer.parseInt(String.valueOf(str[1].charAt(0)));
                        break;
                    case "Starting":
                        monkeys[index] = new Monkey();
                        for (int i = 2; i < str.length; i++) {
//                            System.out.println(str[i]);
                            int item;
                            if (str[i].charAt(str[i].length() - 1) == ',') {
                                item = Integer.parseInt(str[i].substring(0, str[i].length() - 1));
                            } else {
                                item = Integer.parseInt(str[i]);
                            }
                            monkeys[index].addItem(item);
                        }
                        break;
                    case "Operation:":
//                        System.out.println("Ops:" + str[4] + " " + str[5]);
                        if (str[4].charAt(0) == '*') monkeys[index].setEo(EnumOperation.ENUM_VERMENIGVULDIGER);
                        if (str[4].charAt(0) == '+') monkeys[index].setEo(EnumOperation.ENUM_OPTELLER);
                        if (str[5].equals("old")) {
                            monkeys[index].setEo(EnumOperation.ENUM_KWADRAAT);
                            monkeys[index].setOperationValue(0);
                        } else {
                            int item = Integer.parseInt(str[5]);
                            monkeys[index].setOperationValue(item);
                        }
                        break;
                    case "Test:":
//                        System.out.println("Test:" + str[3]);
                        int deler = Integer.parseInt(str[3]);
                        monkeys[index].setTestDeler(deler);
                        break;
                    case "If":
//                        System.out.println("If:" + str[str.length - 1]);
                        int itemTF = Integer.parseInt(str[str.length - 1]);
                        if (str[1].equals("true:")) {
                            monkeys[index].setTestTrue(itemTF);
                        } else {
                            monkeys[index].setTestFalse(itemTF);
                        }
                        break;
                    case "":
                        break;
                    default:
                        System.out.println("Onbekend cmd:" + cmd);
                        break;
                }
            }
            int modTask2 = 1;
            for (int i = 0; i < monkeys.length; i++) {
                modTask2 *= monkeys[i].getTestDeler();
                monkeys[i].print();
            }

            int modPrint = 1;
            int[] inspectedItems = new int[monkeys.length];
            for (int round = 1; round < 10001; round++) {

                for (int i = 0; i < monkeys.length; i++) {
                    List<Integer> items = monkeys[i].removeItems();
                    inspectedItems[i] += items.size();
                    int worryLevel = 0;
                    for (int w :
                            items) {

                        // operation
                        EnumOperation oper = monkeys[i].getEo();
                        switch (oper) {
                            case ENUM_KWADRAAT -> worryLevel = w * w;
                            case ENUM_OPTELLER -> worryLevel = w + monkeys[i].getOperationValue();
                            case ENUM_VERMENIGVULDIGER -> worryLevel = w * monkeys[i].getOperationValue();
                        }
                        // /3
//                        worryLevel = worryLevel / 3;
                        worryLevel %= modTask2;

                        // test
                        if (worryLevel % monkeys[i].getTestDeler() == 0) {
                            int t = monkeys[i].getTestTrue();
                            monkeys[t].addItem(worryLevel);
                        } else {
                            int f = monkeys[i].getTestFalse();
                            monkeys[f].addItem(worryLevel);
                        }
                    }
                }
                if (round % modPrint == 0) {
                    System.out.printf("m %d:", round);
                    for (int i = 0; i < inspectedItems.length; i++) {
                        System.out.printf("[%d]:%d\t", i, inspectedItems[i]);
                    }
                    System.out.println();
                    if (modPrint >= 1000) modPrint += 1000;
                    if (modPrint == 20) modPrint = 1000;
                    if (modPrint == 1) modPrint = 20;
                }
            }

            for (int i = 0; i < monkeys.length; i++) {
                List<Integer> items = monkeys[i].getItems();
                System.out.printf("Monkey %d: ", i);
                System.out.printf(items.toString());
                System.out.println();
            }
            for (int i = 0; i < inspectedItems.length; i++) {
                System.out.printf("ii[%d]: %d\n", i, inspectedItems[i]);
            }
            Arrays.sort(inspectedItems);
            long business = (long) inspectedItems[inspectedItems.length - 1] * inspectedItems[inspectedItems.length - 2];
            System.out.printf("Monkey business: %d", business);
        }
    }

}
// 20726209120