package code.util;

public class OperateOnMatrix{
    
    public static void operateOnEachElement(Object[][] object, MatrixOperationListener l){
        for (int i = 0; i < object.length; i++) {
            for (int j = 0; j < object.length; j++) {
                l.doOperation(object[i][j], i, j);
            }
        }
    }
}
