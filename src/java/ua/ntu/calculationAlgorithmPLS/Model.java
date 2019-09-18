package ua.ntu.calculationAlgorithmPLS;


import org.omg.CORBA.MARSHAL;
import sun.nio.cs.ext.MacArabic;

/**
 * @author A.E.Klochan
 */

public class Model {
    /*
    Клас Model описує модель поляриметричної системи посадки повітряних суден
    fD1 - азимут площини поляризації двічізаломленого променю в І підканалі вимірювання
    fD2 - азимут площини поляризації двічізаломленого променю в ІІ підканалі вимірювання
    fD3 - азимут площини поляризації двічізаломленого променю в ІІI підканалі вимірювання
    fD4 - азимут площини поляризації двічізаломленого променю в ІV підканалі вимірювання
    feL1 - азимут площини поляризації падаючого променю в І каналі вимірювання визначений під час минулого вимірювання
    feL2 - азимут площини поляризації падаючого променю в ІІ каналі вимірювання визначений під час минулого вимірювання
    fe1 - азимут площини поляризації падаючого променю в І каналі вимірювання
    fe2 - азимут площини поляризації падаючого променю в ІІ каналі вимірювання
    f0 - азимут площини поляризації, який відповідає лінії глісади
    iHL - плоский кут падіння випромінювання в горизонтальній площині, визначений під час минулого вимірювання
    iVL - плоский кут падіння випромінювання в вуртикальній площині, визначений під час минулого вимірювання
    ih - плоский кут падіння випромінювання в горизонтальній площині
    iv - плоский кут падіння випромінювання в вертикальній площині
    ipV1 - кут установки пластини в вертикальній площині в першому підканалі
    ipH1- кут установки пластини в горизонтальній площині в першому підканалі
    ipV2 - кут установки пластини в вертикальній площині в другому підканалі
    ipH2 - кут установки пластини в горизонтальній площині в другому підканалі
    n - відносний показник заломлення матеріалу діелектричної пластини
    i1 - просторовий кут падіння в першому каналі
    i2 - просторовий кут падіння в другому каналі
    deltaFid1 - поворот площини поляризації двічізаломленого променю спричений поворотом площини поляризації падаючого
    променю в першому каналі
    deltaFid2 - поворот площини поляризації двічізаломленого променю спричений поворотом площини поляризації падаючого
    променю в другому каналі
    deltaFie - поворот полощини поляризації падаючого променю
     */
    private double ipV1;
    private double ipH1;
    private double ipV2;
    private double ipH2;
    private double f0;
    private static double n;

    private static double iHL;
    private static double iVL;

    private double ih;
    private double iv;
    private double fe1;
    private double fe2;

    private static double ff1;
    private static double ff2;
    private static double fi1;
    private static double fi2;
    private static double i1;
    private static double i2;

    private static double deltaFie;
    private static double deltaFid1;
    private static double deltaFid2;

    public Model(double ipV1, double ipH1, double ipV2, double ipH2, double f0, double n, double deltaFie) {
        this.ipV1 = ipV1 * Math.PI / 180;
        this.ipH1 = ipH1 * Math.PI / 180;
        this.ipV2 = ipV2 * Math.PI / 180;
        this.ipH2 = ipH2 * Math.PI / 180;
        this.f0 = f0 * Math.PI / 180;
        this.iHL = 0.0;
        this.iVL = 0.0;
        this.n = n;
        this.deltaFie = deltaFie * Math.PI / 180;
    }

    public Model(double ipV1, double ipH1, double ipV2, double ipH2, double f0, double n, double iHL, double iVL, double deltaFie) {
        this.ipV1 = ipV1 * Math.PI / 180;
        this.ipH1 = ipH1 * Math.PI / 180;
        this.ipV2 = ipV2 * Math.PI / 180;
        this.ipH2 = ipH2 * Math.PI / 180;
        this.f0 = f0 * Math.PI / 180;
        this.n = n;
        this.iHL = iHL * Math.PI / 180;
        this.iVL = iVL * Math.PI / 180;
        this.deltaFie = deltaFie * Math.PI / 180;
    }

    public void setiHL(double iHL) {
        this.iHL = iHL;
    }

    public void setiVL(double iVL) {
        this.iVL = iVL;
    }

    public double getIh() {
        return ih;
    }

    public double getIv() {
        return iv;
    }

    public double getFe1() {
        return fe1;
    }

    public double getFe2() {
        return fe2;
    }

    public double[] calculationNewAlgorithm(double fD1, double fD2, double fD3, double fD4){
        double[] returnedParameters = new double[5];
        double tanfD1 = Math.tan(fD1);
        double tanfD3 = Math.tan(fD3);
        deltaFid1 = fD2-fD1;
        deltaFid2 = fD4-fD3;
//        коефіцієнти А
        double A1 = (1+(Math.tan(deltaFid1)/tanfD1))/(1-(tanfD1*Math.tan(deltaFid1)));
        double A2 = (1+(Math.tan(deltaFid2)/tanfD3))/(1-(tanfD3*Math.tan(deltaFid2)));
//        тангенси азимутів падаючих променів в першому та другому каналах
        double tanFiE11 = (-(1-A1) - Math.sqrt(Math.pow((1-A1),2)-(4*A1*Math.pow(Math.tan(deltaFie),2))))/(2*A1*Math.tan(deltaFie));
        double tanFiE12 = (-(1-A1) + Math.sqrt(Math.pow((1-A1),2)-(4*A1*Math.pow(Math.tan(deltaFie),2))))/(2*A1*Math.tan(deltaFie));
        double tanFiE21 = (-(1-A2) - Math.sqrt(Math.pow((1-A2),2)-(4*A2*Math.pow(Math.tan(deltaFie),2))))/(2*A2*Math.tan(deltaFie));
        double tanFiE22 = (-(1-A2) + Math.sqrt(Math.pow((1-A2),2)-(4*A2*Math.pow(Math.tan(deltaFie),2))))/(2*A2*Math.tan(deltaFie));
//        азимути падаючих променів
        fe1 = feChoose(tanfD1, tanFiE11, tanFiE12);
        fe2 = feChoose(tanfD3, tanFiE21, tanFiE22);
        returnedParameters[0] = fe1;
        returnedParameters[1] = fe2;
//        просторові кути падіння на першу та другу пластини
        double i11 = Math.atan(-Math.sin(Math.acos(-Math.sqrt(Math.tan(fD1)/Math.tan(fe1))))*n/((n*Math.sqrt(Math.tan(fD1)/Math.tan(fe1)))+1));
        double i12 = Math.atan(Math.sin(Math.acos(Math.sqrt(Math.tan(fD1)/Math.tan(fe1))))*n/((n*Math.sqrt(Math.tan(fD1)/Math.tan(fe1)))-1));
        double i21 = Math.atan(-Math.sin(Math.acos(-Math.sqrt(Math.tan(fD3)/Math.tan(fe2))))*n/((n*Math.sqrt(Math.tan(fD3)/Math.tan(fe2)))+1));
        double i22 = Math.atan(Math.sin(Math.acos(Math.sqrt(Math.tan(fD3)/Math.tan(fe2))))*n/((n*Math.sqrt(Math.tan(fD3)/Math.tan(fe2)))-1));
        i1 = iChoose(i11, i12);
        i2 = iChoose(i21, i22);
//        плоскі кути падіння
        planeAnglesCalculation();
        returnedParameters[2] = iv;
        returnedParameters[3] = ih;
        returnedParameters[4] = f0;
        return returnedParameters;



    }
    private double feChoose(double tanFd, double tanFiE1, double tanFiE2){
        if(Math.abs(1 - (tanFd/tanFiE1)) < Math.abs(1 - (tanFd/tanFiE2)) && tanFd/tanFiE1<1){
            return Math.atan(tanFiE1);
        }
        else if(Math.abs(1 - (tanFd/tanFiE2)) < Math.abs(1 - (tanFd/tanFiE1)) && tanFd/tanFiE2<1){
            return Math.atan(tanFiE2);
        }
        else{
            WrongInputDataException wrongInputDataException = new WrongInputDataException();
            return -1.0;
        }
    }

    private double iChoose(double i1, double i2){
        if(i1>0 && i2<0){
            return i1;
        }
        else if(i2>0 && i1<0){
            return i2;
        }
        else {
            WrongInputDataException wrongInputDataException = new WrongInputDataException();
            return -1.0;
        }
    }
//     метод визначення граничних значень плоских кутів падіння
    private static double findBounder(double variable1, double variable2) {
        if (Math.abs(variable1) < Math.abs(variable2)) {
            return variable1;
        } else {
            return variable2;
        }
    }
        // метод доуточнення початкових значень плоских кутів падіння
    private void planeAngleCheck(double ivMax, double ivMin, double ihMax, double ihMin) {
        if (iVL > ivMax || iVL < ivMin) {
            iVL = (ivMax + ivMin) / 2;
            System.out.println("ivCheck " + iVL);
        }
        if (iHL > ihMax || iHL < ihMin) {
            iHL = (ihMax + ivMin) / 2;
            System.out.println("ihCheck " + iHL);
        }
    }

//    визначення плоских кутів падіння ih, iv
    private void planeAnglesCalculation() {
        double diV = 0.1;
        double diH = 0.1;
        double ivMin;
        double ivMax;
        double ihMin;
        double ihMax;
        double ivMin1 = -i1 - ipV1;
        double ivMin2 = -i2 - ipV2;
        double ivMax1 = i1 - ipV1;
        double ivMax2 = i2 - ipV2;
        double ihMin1 = -i1 - ipH1;
        double ihMin2 = -i2 - ipH2;
        double ihMax1 = i1 - ipH1;
        double ihMax2 = i2 - ipH2;

        ivMin = Math.min(findBounder(ivMax1, ivMax2), findBounder(ivMin1, ivMin2));
        ivMax = Math.max(findBounder(ivMax1, ivMax2), findBounder(ivMin1, ivMin2));
        ihMin = Math.min(findBounder(ihMax1, ihMax2), findBounder(ihMin1, ihMin2));
        ihMax = Math.max(findBounder(ihMax1, ihMax2), findBounder(ihMin1, ihMin2));

        planeAngleCheck(ivMax, ivMin, ihMax, ihMin);
        while (Math.abs(diV) > 0.00001 && Math.abs(diH) > 0.00001) {
            if (Math.abs(i1) < Math.abs(iVL + ipV1) || Math.abs(i1) < Math.abs(iHL + ipH1) ||
                    Math.abs(i2) < Math.abs(iHL + ipH2) || Math.abs(i2) < Math.abs(iVL + ipV2)) {
                WrongInputDataException exception = new WrongInputDataException();
                return;
            } else {
                ih = Math.atan(Math.sqrt(Math.pow(Math.tan(i1), 2) - Math.pow(Math.tan(iVL + ipV1), 2))) - ipH1;
                diH = ih - iHL;
                iHL = ih;

                iv = Math.atan(Math.sqrt(Math.pow(Math.tan(i2), 2) - Math.pow(Math.tan(iHL + ipH2), 2))) - ipV2;
                diV = iv - iVL;
                iVL = iv;
            }
        }
    }

}
    class WrongInputDataException extends Exception{
        public WrongInputDataException() {
            System.out.println("Input data is wrong! Check the inpute data");}

    }
