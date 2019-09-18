package ua.ntu.calculationAlgorithmPLS;

public class Main {
    public static void main(String[] args) {
        double runwayCourse = 120 * Math.PI / 180;
        double glideInstaliationAngle = 3 * Math.PI / 180;
        Model glideModel = new Model(52.5,52.5,52.5,-52.5,67, 2.2, 10);
        Model courseModel = new Model(-52.5,52.5,52.5,52.5,67, 2.2, 10);
        double[] glideParameters = glideModel.calculationNewAlgorithm(1.20281,1.4835,0.99895,1.23409);
        double[] courseParameters = courseModel.calculationNewAlgorithm(1.1386, 1.42131, 1.00074, 1.25692);
        double roolAngel1 = (glideParameters[0] + glideParameters[1] - (2*glideParameters[4]))/2;
        double roolAngel2 = (courseParameters[0] + courseParameters[1] - (2*courseParameters[4]))/2;
        double glideAngularDeviatio = (glideParameters[0] - glideParameters[1])/2;
        double courseAngularDeviatio = (courseParameters[0] - courseParameters[1])/2;
        double yawAngel = courseAngularDeviatio + courseParameters[2] + runwayCourse;
        double pitchAngle = glideAngularDeviatio + glideParameters[3] + glideInstaliationAngle;

        System.out.println(glideParameters[0]*180/Math.PI);
        System.out.println(glideParameters[1]*180/Math.PI);
        System.out.println(glideParameters[2]*180/Math.PI);
        System.out.println(glideParameters[3]*180/Math.PI);
        System.out.println(glideParameters[4]*180/Math.PI);
        System.out.println(courseParameters[0]*180/Math.PI);
        System.out.println(courseParameters[1]*180/Math.PI);
        System.out.println(courseParameters[2]*180/Math.PI);
        System.out.println(courseParameters[3]*180/Math.PI);
        System.out.println(courseParameters[4]*180/Math.PI);



        System.out.println(roolAngel1*180/Math.PI);
        System.out.println(roolAngel2*180/Math.PI);
        System.out.println(glideAngularDeviatio*180/Math.PI);
        System.out.println(courseAngularDeviatio*180/Math.PI);
        System.out.println(yawAngel*180/Math.PI);
        System.out.println(pitchAngle*180/Math.PI);



    }
}
