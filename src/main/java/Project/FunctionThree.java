package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FunctionThree {
    public static void main(String[] args) {
        // 2 for Jerry Position, 3 for Tom position
//        int[][] arr1 = {
//                {1,1,1,1,1},
//                {2,0,0,0,3},
//                {1,1,1,1,1},
//                {1,1,1,1,1},
//                {1,1,1,1,1}
//        };

        int[][] arr1 = {
                {1,1,1,1,1},
                {2,0,1,1,1},
                {1,0,1,1,1},
                {1,0,0,0,3},
                {1,1,1,1,1}
        };

        // assume jerry doesn't have any movement
        //ArrayList<Integer> path1 = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9));
        ArrayList<Integer> path1 = new ArrayList<>(Arrays.asList(5, 6, 11, 16, 17, 18, 19)); //not used
        int Exit = path1.get(path1.size() - 1);
        int Entry = path1.get(0);

        final String[] jerryCurrentDirection = {"REST"};
        String tomCurrentDirection = "LEFT"; //hardcode, not using currently //tom doesnt really need

        int n = 5; //n*n map, hardcode

//        int virtualMovementIncrement = 5; //hardcode
//        int horioztalMovementIncrement = 1; //fixed value

        final int[] tomPositionInList = {path1.size() - 1};
        final int[] tomCurrentPosition = {path1.get(tomPositionInList[0])};

        int jerryPositionInList = 0;
        int jerryCurrentPosition = path1.get(jerryPositionInList);

//        for(int i = 0;i < path1.size();i++){ //get position of tom and jerry based on given map/array
        // this function may not be used in future
//                if(tomCurrentPosition != -1 && tomCurrentPosition != -1){
//                    break;
//                }
//                if(path1.get(i) == 2){
//                    jerryCurrentPosition = i;
//                }
//                else if(path1.get(i) == 3){
//                    tomCurrentPosition = i;
//                }
//
//        }
//
//        static void changeTomPosition(int[][] array){
//            System.out.println(array);
//        }

        Timer tomAction = new Timer();
        TimerTask UpdateTomPosition = new TimerTask() {
            public void run() {
                int tomNextPosition = tomCurrentPosition[0];

                if(--tomPositionInList[0] >= 0) {
                    tomNextPosition = path1.get(tomPositionInList[0]);
                    int column = tomNextPosition % n;
                    int row = tomNextPosition / n;

                    int old_column = tomCurrentPosition[0] % n;
                    int old_row = tomCurrentPosition[0] / n;
                    tomCurrentPosition[0] = tomNextPosition;

                    arr1[old_row][old_column] = 0;
                    arr1[row][column] = 3; //new tom posit ion

                    for(int i = 0; i < arr1.length; i++) {
                        System.out.println(Arrays.toString(arr1[i]));
                    }
                }
                else{
                    cancel();
                    //jerryAction.cancel();
                    System.out.println("Tom catches Jerry!");
                }
            }
        };

        tomAction.scheduleAtFixedRate(UpdateTomPosition, 0, 500);// Tom moves between 0.5s

        Timer jerryAction = new Timer(); //not used
        TimerTask UpdateJerryPosition = new TimerTask() { //not used, this part need to cooperate with FunctionB
            public void run() {
                int jerryNextPosition = jerryCurrentPosition;
                switch(jerryCurrentDirection[0]){
                    case "REST" -> jerryNextPosition = jerryNextPosition;
                    case "TOP" -> jerryNextPosition = jerryNextPosition + n;
                    case "RIGHT" -> jerryNextPosition = jerryNextPosition + 1;
                    case "BOTTOM" -> jerryNextPosition = jerryNextPosition + n;
                    case "LEFT" -> jerryNextPosition = jerryNextPosition - 1;
                }

                int column = jerryNextPosition % n;
                int row = jerryNextPosition / n;

                int old_column = jerryCurrentPosition % n;
                int old_row = jerryNextPosition / n;
                if(arr1[row][column] == 0){
                    arr1[row][column] = 2;
                    arr1[old_row][old_column] = 0;
                    if(jerryNextPosition == Exit){
                        cancel();
                        tomAction.cancel();
                        System.out.println("Jerry Win!");
                    }
                }
            }
        };

        //jerryAction.scheduleAtFixedRate(UpdateJerryPosition, 0, 700);// Jerry moves between 0.7s
//        jerry.addKeyListner( // FOR FUTURE USE
//                new KeyListenser() {
//                    public void keyPressed(KeyEvent e){
//                        if(e.getKeyCode() == KeyEvent.VK_A){
//
//                        }
//                        switch(e.getKeyCode()){
//                            case KeyEvent.VK_A -> jerryCurrentDirection[0] = "LEFT";
//                            case KeyEvent.VK_W -> jerryCurrentDirection[0] = "TOP";
//                            case KeyEvent.VK_D -> jerryCurrentDirection[0] = "RIGHT";
//                            case KeyEvent.VK_S -> jerryCurrentDirection[0] = "BOTTOM";
//                        }
//                    }
//                }
//        );
    }
}