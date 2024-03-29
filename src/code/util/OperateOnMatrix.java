package code.util;

public final class OperateOnMatrix{
    //For each element of a matrix, does a certain operation
    public static <X> void operateOnEachElement(X[][] object, MatrixOperationListener l){
        for (int i = 0; i < object.length; ++i) {
            for (int j = 0; j < object.length; ++j) {
                l.doOperation(i, j);
            }
        }
    }
}
