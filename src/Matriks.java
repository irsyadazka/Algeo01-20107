package src;

import java.util.*;
import java.io.*;

public class Matriks {
    public int RowMin = 0, RowMax = 100;
    public int ColMin = 0, ColMax = 100;
    public int Row, Col, countSwap;
    public double saveDividers = 1;
    public double[][] Mtrx;
    public boolean hasInverse;
    Scanner scanner = new Scanner(System.in);

    public void createMatriks(int row, int col) {
        this.Row = row;
        this.Col = col;
        Mtrx = new double[row][col];
    }

    public int getFirstIdxRow() {
        return RowMin;
    }

    public int getFirstIdxCol() {
        return ColMin;
    }

    public int getLastIdxRow() {
        return this.Row - 1;
    }

    public int getLastIdxCol() {
        return this.Col - 1;
    }

    public int countElmt() {
        return this.Row * this.Col;
    }

    public double getElmt(int row, int col) {
        return this.Mtrx[row][col];
    }

    public void setElmt(int row, int col) {
        this.Mtrx[row][col] = scanner.nextDouble();
    }

    public void setValueElmt(int row, int col, double value) {
        this.Mtrx[row][col] = value;
    }

    public void readMatrix() {
        int i, j;
        for (i = RowMin; i < this.Row; i++) {
            for (j = ColMin; j < this.Col; j++) {
                setElmt(i, j);
            }
        }
    }

    public void readMatrixHilbert(int n) {
        int i, j;
        for (i = RowMin; i < n; i++) {
            for (j = ColMin; j < n; j++) {
                setValueElmt(i, j, 1);
            }
        }
        for (i = RowMin; i < n; i++) {
            for (j = ColMin; j < n; j++) {
                this.Mtrx[i][j] /= (i+j+1);
            }
        }
    }
    
    public void polynomRead() {
        double an[];
        double bn[];
        an = new double[this.Col];
        bn = new double[this.Col];
        int i, j;
        j = 0;

        for (i = 0; i < this.Row; i++) {
            this.Mtrx[i][j] = 1;
        }

        for (i = 0; i < this.Row; i++) {
            System.out.printf("Masukkan nilai untuk titik ke-%d : ", i + 1);
            an[i] = scanner.nextDouble();
            bn[i] = scanner.nextDouble();
            for (j = 1; j < this.Col - 1; j++) {
                this.Mtrx[i][j] = (double) Math.pow(an[i], j);
            }
        }
        for (i = RowMin; i < this.Row; i++) {
            this.Mtrx[i][this.Col - 1] = bn[i];
        }
    }
    
    public void readPolynomfromFile() throws IOException {
        String fileName;
        fileName = scanner.nextLine();
        try {
            ArrayList<String> num = new ArrayList<String>();
            int countrow = 0;
            //int countcol = 0;
            BufferedReader input = new BufferedReader(new FileReader("../test/" + fileName));
            String readLine = input.readLine();
            while (readLine != null) {
                String[] readLineS = readLine.split(" ");
                for (String x : readLineS) {
                    num.add(x);
                }
                countrow++;
                //countcol = readLineS.length;
                readLine = input.readLine();
            }
            input.close();
            this.createMatriks(countrow, countrow+1);
            int i, j;
            double an[];
            double bn[];
            an = new double[countrow+1];
            bn = new double[countrow+1];
            j = 0;

            for (i = 0; i < countrow; i++) {
                this.Mtrx[i][j] = 1;
            }
            int param = 0;
            for (i = RowMin; i < countrow; i++) {
                an[i] = Double.parseDouble(num.get(param));
                bn[i] = Double.parseDouble(num.get(param+1));
                param = param+2;
            }
            for (i = 0; i < countrow; i++) {
                for (j = 1; j < countrow; j++) {
                    this.Mtrx[i][j] = (double) Math.pow(an[i], j);
                }
            }
            for (i = RowMin; i < this.Row; i++) {
                this.Mtrx[i][this.Col - 1] = bn[i];
            }
        } catch (Exception ie) {
            System.out.printf("Oww ada kesalahan!");
            this.createMatriks(0, 0);
        }
    }
    
    public void readMatrixfromFile() throws IOException {
        String fileName;
        fileName = scanner.nextLine();
        try {
            ArrayList<String> num = new ArrayList<String>();
            int countrow = 0;
            int countcol = 0;
            BufferedReader input = new BufferedReader(new FileReader("../test/" + fileName));
            String readLine = input.readLine();
            while (readLine != null) {
                String[] readLineS = readLine.split(" ");
                for (String x : readLineS) {
                    num.add(x);
                }
                countrow++;
                countcol = readLineS.length;
                readLine = input.readLine();
            }
            input.close();
            this.createMatriks(countrow, countcol);
            int i, j;
            int param = 0;
            for (i = RowMin; i < countrow; i++) {
                for (j = ColMin; j < countcol; j++) {
                    setValueElmt(i, j, Double.parseDouble(num.get(param)));
                    param++;
                }
            }
        } catch (Exception ie) {
            System.out.printf("Oww ada kesalahan!");
            this.createMatriks(0, 0);
        }
    }

    public void writeMatrix() {
        int i, j;
        for (i = RowMin; i < this.Row; i++) {
            for (j = ColMin; j < this.Col; j++) {
                System.out.printf("%.2f ", getElmt(i, j));
            }
            System.out.print("\n");
        }    
    }

    public void writeMatrixInvToFile() {
        try {
            FileWriter writer = new FileWriter("outputInvers.txt");
            int i, j;
            for (i = RowMin; i < this.Row; i++) {
                for (j = ColMin; j < this.Col; j++) {
                    writer.write(getElmt(i, j) + " ");
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("\nBerhasil dicetak di file");
        } catch (IOException e) {
            System.out.println("Ada error: ");
            e.printStackTrace();
        }
    }

    public void writeMatrixDetToFile() {
        try {
            FileWriter writer = new FileWriter("outputDeterminanOBE.txt");
            int i, j;
            for (i = RowMin; i < this.Row; i++) {
                for (j = ColMin; j < this.Col; j++) {
                    writer.write(getElmt(i, j) + " ");
                }
                writer.write("\n");
            }
            writer.write("Determinan dari matriks tersebut adalah: " + determinantOBE());
            writer.close();
            System.out.println("\nBerhasil dicetak di file");
        } catch (IOException e) {
            System.out.println("Ada error: ");
            e.printStackTrace();
        }
    }

    public void writeMatrixDetCofToFile(Matriks m) {
        try {
            FileWriter writer = new FileWriter("outputDeterminanCof.txt");
            int i, j;
            for (i = RowMin; i < this.Row; i++) {
                for (j = ColMin; j < this.Col; j++) {
                    writer.write(getElmt(i, j) + " ");
                }
                writer.write("\n");
            }
            writer.write("Determinan dari matriks tersebut adalah: " + determinantC(m));
            writer.close();
            System.out.println("\nBerhasil dicetak di file");
        } catch (IOException e) {
            System.out.println("Ada error: ");
            e.printStackTrace();
        }
    }
    
    public boolean isSquare() {
        return (this.Row == this.Col);
    }

    public boolean isIdentity(Matriks m) {
        int i, j;
        boolean ident = true;
        for (i = RowMin; i < this.Row; i++) {
            for (j = ColMin; j < this.Col; j++) {
                if ((i == j) && (m.Mtrx[i][j] != 1)) {
                    return false;
                }
                if ((i != j) && (m.Mtrx[i][j] != 0)) {
                    return false;
                }
            }
        }
        return ident;
    }

    public boolean isZero(int row, int col) {
        return getElmt(row, col) == 0;
    }

    public boolean isBelowRowZero(int row, int col) {
        int i;
        if (row + 1 >= this.Row) {
            return true;
        }
        for (i = row + 1; i < this.Row; i++) {
            if (!isZero(i, col)) {
                return false;
            }
        }
        return true;
    }

    public void Swap(int row1, int row2) {
        double temp;
        for (int j = ColMin; j < this.Col; j++) {
            temp = getElmt(row2, j);
            this.Mtrx[row2][j] = getElmt(row1, j);
            this.Mtrx[row1][j] = temp;
        }
    }

    public void swapZero(int row, int col) {
        int i, count;
        count = 0;
        for (i = row + 1; i < this.Row; i++) {
            if (!isZero(i, col)) {
                Swap(row, i);
                break;
            }
        }
        count++;
        this.countSwap = count;
    }

    public void multiplyRow(int row, double x) {
        int j;
        for (j = 0; j < this.Col; j++) {
            this.Mtrx[row][j] = x*getElmt(row, j);
        }
    }

    public void multiplyCol(int col, double x) {
        int i;
        for (i = 0; i < this.Row; i++) {
            this.Mtrx[i][col] = x*getElmt(i, col);
        }
    }

    public void divideRow(int row, double x) {
        int j;
        for (j = 0; j < this.Col; j++) {
            this.Mtrx[row][j] = getElmt(row, j)/x;
        }
    }

    public void plusRow(int row1, int row2, double x) {
        int j;
        for (j = 0; j < this.Col; j++) {
            this.Mtrx[row1][j] += x*getElmt(row2, j);
        }
    }

    public void minusRow(int row1, int row2, double x) {
        plusRow(row1, row2, -x);
    }

    public void transpose(){
        for (int i = RowMin; i < this.Row; i++){
            for (int j = ColMin; j < i; j++){
                double temp;
                temp = getElmt(j, i);
                this.Mtrx[j][i] = getElmt(i, j);
                this.Mtrx[i][j] = temp;
            }
        }
    } 

    public void elimGauss() {
        int i, j;
        double multiply, divide;
        int zeroCol = 0;
        for (i = RowMin; i < this.Row; i++) {
            for (j = i+zeroCol; j < this.Col; j++) {
                if (isZero(i, j)) {
                    if (isBelowRowZero(i, j)) {
                        zeroCol++;
                    } else {
                        swapZero(i, j);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (j >= this.Col) {
                break;
            }
            divide = getElmt(i, j);
            while (divide == 0) {
                j++;
                divide = getElmt(i, j);
            }
            this.saveDividers *= divide;
            int k;
            for (k = j; k < this.Col; k++) {
                this.Mtrx[i][k] /= divide;
            }
            for (k = i+1; k < this.Row; k++) {
                multiply = getElmt(k, j);
                for (int subj = j; subj < this.Col; subj++) {
                    this.Mtrx[k][subj] -= getElmt(i, subj)*multiply;
                }
            }
        }
    }

    public void elimGaussJordan() {
        int i, j;
        double multiply, divide;
        int zeroCol = 0;
        for (i = RowMin; i < this.Row; i++) {
            for (j = i+zeroCol; j < this.Col; j++) {
                if (isZero(i, j)) {
                    if (isBelowRowZero(i, j)) {
                        zeroCol++;
                    } else {
                        swapZero(i, j);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (j >= this.Col) {
                break;
            }
            divide = getElmt(i, j);
            while (divide == 0) {
                j++;
                divide = getElmt(i, j);
            }
            int k;
            for (k = j; k < this.Col; k++) {
                this.Mtrx[i][k] /= divide;
            }
            for (k = RowMin; k < this.Row; k++) {
                if (k == i) {
                    continue;
                } else {
                    multiply = getElmt(k, j);
                    for (int subj = j; subj < this.Col; subj++) {
                        this.Mtrx[k][subj] -= getElmt(i, subj)*multiply;
                    } 
                }
            }
        }
    }

    public double determinantOBE() {
        double determinant = 1;
        elimGauss();
        for (int i = RowMin; i < this.Row; i++) {
            determinant *= getElmt(i, i);
        }
        if (this.countSwap % 2 == 1) {
            determinant = -(determinant);
        }
        determinant *= this.saveDividers;
        return determinant;
    }
    
    public void getDeterminantOBE(){
        System.out.println("determinan dari matriks yang diberikan adalah " + this.determinantOBE());
    }
    
    public Matriks subMatriks(int i, int j) {
        Matriks m = new Matriks();
        m.createMatriks(this.Row - 1, this.Col - 1); 

        int p = 0, q = 0;
        for (int row = RowMin; row < this.Row; row++) {
            if(row == i) continue; 
            for (int col = ColMin; col < this.Col; col++) {
                if(col == j) continue;
                m.Mtrx[p][q] = getElmt(row, col);
                q = (q + 1)%(this.Row - 1);
                if(q == 0) {
                    p++;
                }
            }
        }
        return m;
    }

    public double determinantC(Matriks m) {
        Matriks sub;
        for (int i = RowMin; i < this.Row; i++) {
            for (int j = ColMin; j < this.Col; j++) {
                sub = subMatriks(i, j);
                m.Mtrx[i][j] = (double) (Math.pow(-1, i + j) * sub.determinantOBE());
            }
        }
        double det = 0;
        int i = 0;
        for (int j = ColMin; j < this.Col; j++) {
            det += getElmt(i, j) * m.Mtrx[i][j];
        }
        return det;
    }

    public void getDeterminantC(Matriks m){
        System.out.println("determinan dari matriks yang diberikan adalah " + this.determinantC(m));
    }

    public Matriks cofactor(Matriks m){
        Matriks sub;
        for (int i = RowMin; i < this.Row; i++) {
            for (int j = ColMin; j < this.Col; j++) {
                sub = subMatriks(i, j);
                m.Mtrx[i][j] = (double) (Math.pow(-1, i + j) * sub.determinantOBE());
            }
        }
        return m;
    }

    public void kaidah_crammer(boolean toFile) {
        double det;
        double subdet;
        Matriks m1 = new Matriks();
        Matriks m2 = new Matriks();

        m1.createMatriks(this.Row, this.Col-1);
        for (int i = RowMin; i < this.Row; i++) {
            for (int j = ColMin; j < this.Col-1; j++) {
                m1.Mtrx[i][j] = getElmt(i, j);
            }
        }
        m2.createMatriks(this.Row, 1);
        for (int k = RowMin; k < this.Row; k++) {
            m2.Mtrx[k][0] = getElmt(k, this.Col-1);
        }

        det = determinantOBE();
        if (det == 0) {
            System.out.printf("Determinan = 0, tidak bisa digunakan kaidah cramer");
        }
        else {
            double sol;
            for (int a = ColMin; a < this.Col-1; a++) {
                Matriks submatrix = new Matriks();
                submatrix.createMatriks(this.Row, this.Col-1);
                for (int b = ColMin; b < this.Col-1; b++) {
                    for (int c = RowMin; c < this.Row; c++) {
                        if (a == b) {
                            submatrix.Mtrx[c][b] = m2.Mtrx[c][0];
                        }
                        else {
                            submatrix.Mtrx[c][b] = m1.Mtrx[c][b];
                        }
                    }
                }
                subdet = submatrix.determinantOBE();
                sol = (subdet/det);
                System.out.printf("solusi X%d = ", a+1);
                System.out.printf("%.2f ", sol);
            }
        }

        if (toFile) {
            try {
                FileWriter writer = new FileWriter("outputSolusiSPL.txt");
                int i, j;
                for (i = RowMin; i < this.Row; i++) {
                    for (j = ColMin; j < this.Col; j++) {
                        writer.write(getElmt(i, j) + " ");
                    }
                    writer.write("\n");
                }
                if (det == 0) {
                    writer.write("Determinan = 0, tidak bisa digunakan kaidah cramer");
                } else {
                    double sol;
                    for (int a = ColMin; a < this.Col-1; a++) {
                        Matriks submatrix = new Matriks();
                        submatrix.createMatriks(this.Row, this.Col-1);
                        for (int b = ColMin; b < this.Col-1; b++) {
                            for (int c = RowMin; c < this.Row; c++) {
                                if (a == b) {
                                    submatrix.Mtrx[c][b] = m2.Mtrx[c][0];
                                }
                                else {
                                    submatrix.Mtrx[c][b] = m1.Mtrx[c][b];
                                }
                            }
                        }
                        subdet = submatrix.determinantOBE();
                        sol = (subdet/det);
                        writer.write("solusi X" + a+1 + " = " + sol + " ");
                    }
                }
                writer.close();
                System.out.println("Berhasil dicetak di file");
            } catch (IOException e) {
                System.out.println("Ada error: ");
                e.printStackTrace();
            }
        }
    }

    public void adjoint_invers(){
        Matriks n = new Matriks();
        Matriks o = new Matriks();

        n.createMatriks(this.Row, this.Col);
        o.createMatriks(this.Row, this.Col);

        o = this.cofactor(n);
        System.out.println("\nKofaktor dari matriks tersebut adalah:\n");
        o.writeMatrix();
        System.out.println("\n");

        o.transpose();
        System.out.println("Adjoint dari matriks tersebut adalah:\n");
        o.writeMatrix();
        System.out.println("\n");

        double det;
        det = this.determinantOBE();

        if (det == 0){
            System.out.println("Matriks tidak bersifat invertible karena determinan matriks = 0");
        }

        else{
            for(int i = RowMin; i < this.Row;i++){
                for(int j = ColMin; j < this.Col; j++){
                    o.Mtrx[i][j] = ((1/det)*(o.Mtrx[i][j]));
                }
            }
            System.out.println("Sehingga balikan dari matriks tersebut adalah: \n");
            this.Mtrx = o.Mtrx;
            this.writeMatrix();
            System.out.println("\n");
        }
    }

    public void inversGaussWrite() {
        Matriks inv = new Matriks();
        inv.createMatriks(this.Row, this.Col);
        int i, j;
        for (i = RowMin; i < this.Row; i++) {
            inv.Mtrx[i][i] = 1;
        }
        double divide, multiply;
        int zeroCol = 0;
        for (i = RowMin; i < this.Row; i++) {
            for (j = i + zeroCol; j < this.Col; j++) {
                if (isZero(i, i+zeroCol)) {
                    if (isBelowRowZero(i, i)) {
                        zeroCol++;
                    } else {
                        int a;
                        for (a = i + 1; a < this.Row; a++) {
                            if (!isZero(a, i)) {
                                double temp;
                                for (j = 0; j < this.Col; j++) {
                                    temp = getElmt(a, j);
                                    this.Mtrx[a][j] = getElmt(i, j);
                                    this.Mtrx[i][j] = temp;
                                    temp = inv.Mtrx[a][j];
                                    inv.Mtrx[a][j] = inv.Mtrx[i][j];
                                    inv.Mtrx[i][j] = temp;
                                }
                                break;
                            }
                        }
                        break;
                    }
                } else {
                    break;
                }
            }

            if (j >= this.Col) {
                break;
            }
            divide = getElmt(i, j);
            while (divide == 0) {
                j++;
                divide = getElmt(i, j);
            }
            int k;
            for (k = j; k < this.Col; k++) {
                this.Mtrx[i][k] /= divide;
            }
            for (k = 0; k < this.Col; k++) {
                inv.Mtrx[i][k] /= divide;
            }
            for (k = i+1; k < this.Row; k++) {
                multiply = getElmt(k, j);
                int subj;
                for (subj = j; subj < this.Col; subj++) {
                    this.Mtrx[k][subj] -= multiply*getElmt(i,subj);
                }
                for (subj = ColMin; subj < this.Col; subj++) {
                    inv.Mtrx[k][subj] -= multiply*inv.Mtrx[i][subj];
                }
            } 
        }
        for (i = RowMin; i < this.Row; i++) {
            for (j = i; j < this.Col; j++) {
                if (getElmt(i, j) == 1) {
                    int k;
                    for (k = 0; k < i; k++) {
                        if (!isZero(k, j)) {
                            multiply = getElmt(k, j);
                            for (int subj = j; subj < this.Col; subj++) {
                                this.Mtrx[k][subj] -= getElmt(i, subj)*multiply;
                            }
                            for (int subj = ColMin; subj < this.Col; subj++) {
                                inv.Mtrx[k][subj] -= inv.Mtrx[i][subj]*multiply;
                            }
                        }
                    }
                }
            }
        }
        if ((!isIdentity(this))) {
            System.out.println("Matriks tidak mempunyai invers");
            this.hasInverse = false;
        } else {
            this.Mtrx = inv.Mtrx;
            this.writeMatrix();
            this.hasInverse = true;
        }
    }

    void findsplwithGauss(boolean toFile){
        this.elimGauss();
        boolean noSol = false;
        boolean multSol = false;
        if (this.Row == this.Col-1){
            if (this.Mtrx[this.Row-1][this.Col-1] != 0d) {
                noSol = true;
                for (int i=0; i < this.Col-2; i++) {
                    if (this.Mtrx[this.Row-1][i] == 0d) {
                        noSol = false;
                    }
                }
            }
            else {
                multSol = true;
                for (int j=0; j < this.Col-2; j++){
                    if (this.Mtrx[this.Row-1][j] != 0d) {
                        multSol = false;
                    }
                }
            }
        }
        else if (this.Row >= this.Col){
            if (this.Mtrx[this.Col-2][this.Col-1] != 0d) {
                noSol = true;
                for (int i=0; i < this.Col-1; i++) {
                    if (this.Mtrx[this.Col-2][i] != 0d) {
                        noSol = false;
                    }
                }
            }
            else {
                multSol = true;
                for (int j=0; j < this.Col-1; j++){
                    if (this.Mtrx[this.Col-2][j] == 0d) {
                        multSol = false;
                    }
                }
            }         
        }
        else{
            multSol = true;
        }
        if(toFile){
            try {
                FileWriter writer = new FileWriter("outputfindSPLwithGauss.txt");
                if (noSol) {
                    writer.write("SPL tidak memiliki solusi");
                }
                else if (multSol) {
                    writer.write("SPL memiliki banyak solusi");
                    writer.write("\n");
                    char [] param = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                    for (int i=0; i < this.Col-1; i++){
                        writer.write("X" + (i+1) + " = ");
                        writer.write(param[i]);
                        writer.write("\n");
                    }
                    for (int i=0; i < this.Row; i++) {
                        if (this.Mtrx[i][i] == 1d) {
                            if (this.Mtrx[i][this.Col-1] != 0d){
                                writer.write("X"+ (i+1) +" = " + this.Mtrx[i][this.Col-1]);
                                for (int j=i+1; j<this.Col-1; j++){
                                    if (this.Mtrx[i][j] != 0){
                                        if (this.Mtrx[i][j] < 0d) {
                                            writer.write("+ " + Math.abs((this.Mtrx[i][j])) + param[j] + " ");
                                        }
                                        else {
                                            writer.write("-" + (this.Mtrx[i][j]) + param[j] + " ");
                                        }
                                    }
                                }                        
                            }
                            else {
                                writer.write("X"+ (i+1) +" = " );
                                for (int j=i+1; j<this.Col-1; j++){
                                    if (this.Mtrx[i][j] != 0){
                                        writer.write(" "+ Math.abs((this.Mtrx[i][j])) + param[j] + " ");
                                    }
                                }                        
                            }
                        }
                        else {
                            int temp = i;
                            int temp2 = i+1;
                            if (this.Mtrx[temp][this.Col-1] != 0d){
                                if(this.Mtrx[temp][temp2] == 0){
                                    temp2 +=1;
                                    writer.write("X" + (temp2+1) + " = " + this.Mtrx[temp][this.Col-1]);
                                    for (int j=temp2+1; j<this.Col-1; j++){
                                        if (this.Mtrx[temp][j] != 0){
                                            if (this.Mtrx[temp][j] < 0d) {
                                                writer.write(" + " + Math.abs((this.Mtrx[temp][j])) + param[j] + " ");
                                            }
                                            else {
                                                writer.write(" - " + (this.Mtrx[temp][j]) + param[j] + " ");
                                            }
                                        }
                                    } 
                                }
                                else{
                                    writer.write("X" + (temp2+1) + " = " + this.Mtrx[temp][this.Col-1]);
                                    for (int j=temp2+1; j<this.Col-1; j++){
                                        if (this.Mtrx[temp][j] != 0){
                                            if (this.Mtrx[temp][j] < 0d) {
                                                writer.write(" + " + Math.abs((this.Mtrx[temp][j])) + param[j] + " ");
                                            }
                                            else {
                                                writer.write(" - " + (this.Mtrx[temp][j]) + param[j] + " ");
                                            }
                                        }
                                    } 
                                }                       
                            }
                        }
                    }
                }
                else {
                    writer.write("SPL memiliki solusi unik");
                    writer.write("");
                    double sol[] =  new double[this.Col-1];
                    for (int k = this.Col-2; k >= 0; k--){
                        sol[k] = this.Mtrx[k][this.Col-1];
                        for (int l = k+1; l < this.Col-1; l++){
                            sol[k] -= this.Mtrx[k][l]*sol[l];
                        }
                        sol[k] /= this.Mtrx[k][k];
                    }
                    writer.write("Solusi adalah:");
                    writer.write(" ");
                    for (int i = 0; i < this.Col-1; i++)
                    {
                        writer.write("X" + (i+1) + " = " + sol[i] + " ");
                        writer.write("");
                    }
                }
                writer.close();
                System.out.println("Berhasil dicetak di file");
            }
            catch (IOException e) {
                System.out.println("Ada error: ");
                e.printStackTrace();
            }
        }
        else{
            if (noSol) {
                System.out.printf("SPL tidak memiliki solusi");
            }
            else if (multSol) {
                System.out.printf("SPL memiliki banyak solusi");
                System.out.println();
                char [] param = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                for (int i=0; i < this.Col-1; i++){
                    System.out.printf("X%d = ", i+1);
                    System.out.printf("%c", param[i]);
                    System.out.println();
                }
                for (int i=0; i < this.Row; i++) {
                    if (this.Mtrx[i][i] == 1d) {
                        if (this.Mtrx[i][this.Col-1] != 0d){
                            System.out.printf("X%d = %.2f ", i+1, this.Mtrx[i][this.Col-1]);
                            for (int j=i+1; j<this.Col-1; j++){
                                if (this.Mtrx[i][j] != 0){
                                    if (this.Mtrx[i][j] < 0d) {
                                        System.out.printf("+ %.2f", -(this.Mtrx[i][j]));
                                        System.out.printf("%c ", param[j]);
                                    }
                                    else {
                                        System.out.printf("%.2f", -(this.Mtrx[i][j]));
                                        System.out.printf("%c ", param[j]);
                                    }
                                }
                            }                        
                        }
                        else {
                            System.out.printf("X%d = ", i+1);
                            for (int j=i+1; j<this.Col-1; j++){
                                if (this.Mtrx[i][j] != 0){
                                    System.out.printf("%.2f", -(this.Mtrx[i][j]));
                                    System.out.printf("%c ", param[j]);
                                }
                            }                        
                        }
                    }
                    else {
                        int temp = i;
                        int temp2 = i+1;
                        if (this.Mtrx[temp][this.Col-1] != 0d){
                            if(this.Mtrx[temp][temp2] == 0){
                                temp2 +=1;
                                System.out.printf("X%d = %.2f ", temp2+1, this.Mtrx[temp][this.Col-1]);
                                for (int j=temp2+1; j<this.Col-1; j++){
                                    if (this.Mtrx[temp][j] != 0){
                                        if (this.Mtrx[temp][j] < 0d) {
                                            System.out.printf("+ %.2f", -(this.Mtrx[temp][j]));
                                            System.out.printf("%c ", param[j]);
                                        }
                                        else {
                                            System.out.printf("%.2f", -(this.Mtrx[temp][j]));
                                            System.out.printf("%c ", param[j]);
                                        }
                                    }
                                } 
                            }
                            else{
                                System.out.printf("X%d = %.2f ", temp2+1, this.Mtrx[temp][this.Col-1]);
                                for (int j=temp2+1; j<this.Col-1; j++){
                                    if (this.Mtrx[temp][j] != 0){
                                        if (this.Mtrx[temp][j] < 0d) {
                                            System.out.printf("+ %.2f", -(this.Mtrx[temp][j]));
                                            System.out.printf("%c ", param[j]);
                                        }
                                        else {
                                            System.out.printf("%.2f", -(this.Mtrx[temp][j]));
                                            System.out.printf("%c ", param[j]);
                                        }
                                    }
                                } 
                            }                       
                        }
                    }
                }
            }
            else {
                System.out.printf("SPL memiliki solusi unik");
                System.out.println();
                double sol[] =  new double[this.Col-1];
                for (int k = this.Col-2; k >= 0; k--){
                    sol[k] = this.Mtrx[k][this.Col-1];
                    for (int l = k+1; l < this.Col-1; l++){
                        sol[k] -= this.Mtrx[k][l]*sol[l];
                    }
                    sol[k] /= this.Mtrx[k][k];
                }
                System.out.printf("Solusi adalah:");
                System.out.println();
                for (int i = 0; i < this.Col-1; i++)
                {
                    System.out.printf("X%d = ", i+1);
                    System.out.printf("%.2f", sol[i]);
                    System.out.println();
                }
            }
        }
    }

    public void findsplwithGaussJordan(boolean toFile){
        this.elimGaussJordan();
        boolean noSol = false;
        boolean multSol = false;
        if (this.Row == this.Col-1){
            if (this.Mtrx[this.Row-1][this.Col-1] != 0d) {
                noSol = true;
                for (int i=0; i < this.Col-2; i++) {
                    if (this.Mtrx[this.Row-1][i] == 0d) {
                        noSol = false;
                    }
                }
            }
            else {
                multSol = true;
                for (int j=0; j < this.Col-2; j++){
                    if (this.Mtrx[this.Row-1][j] != 0d) {
                        multSol = false;
                    }
                }
            }
        }
        else if (this.Row >= this.Col){
            if (this.Mtrx[this.Col-2][this.Col-1] != 0d) {
                noSol = true;
                for (int i=0; i < this.Col-1; i++) {
                    if (this.Mtrx[this.Col-2][i] != 0d) {
                        noSol = false;
                    }
                }
            }
            else {
                multSol = true;
                for (int j=0; j < this.Col-1; j++){
                    if (this.Mtrx[this.Col-2][j] == 0d) {
                        multSol = false;
                    }
                }
            }         
        }
        else{
            multSol = true;
        }
        if(toFile){
            try {
                FileWriter writer = new FileWriter("outputfindSPLwithGaussJordan.txt");
                if (noSol) {
                    writer.write("SPL tidak memiliki solusi");
                }
                else if (multSol) {
                    writer.write("SPL memiliki banyak solusi");
                    writer.write("\n");
                    char [] param = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                    for (int i=0; i < this.Col-1; i++){
                        writer.write("X" + (i+1) + " = ");
                        writer.write(param[i]);
                        writer.write("\n");
                    }
                    for (int i=0; i < this.Row; i++) {
                        if (this.Mtrx[i][i] == 1d) {
                            if (this.Mtrx[i][this.Col-1] != 0d){
                                writer.write("X"+ (i+1) +" = " + this.Mtrx[i][this.Col-1]);
                                for (int j=i+1; j<this.Col-1; j++){
                                    if (this.Mtrx[i][j] != 0){
                                        if (this.Mtrx[i][j] < 0d) {
                                            writer.write("+ " + Math.abs((this.Mtrx[i][j])) + param[j] + " ");
                                        }
                                        else {
                                            writer.write("-" + (this.Mtrx[i][j]) + param[j] + " ");
                                        }
                                    }
                                }                        
                            }
                            else {
                                writer.write("X"+ (i+1) +" = " );
                                for (int j=i+1; j<this.Col-1; j++){
                                    if (this.Mtrx[i][j] != 0){
                                        writer.write(" "+ Math.abs((this.Mtrx[i][j])) + param[j] + " ");
                                    }
                                }                        
                            }
                        }
                        else {
                            int temp = i;
                            int temp2 = i+1;
                            if (this.Mtrx[temp][this.Col-1] != 0d){
                                if(this.Mtrx[temp][temp2] == 0){
                                    temp2 +=1;
                                    writer.write("X" + (temp2+1) + " = " + this.Mtrx[temp][this.Col-1]);
                                    for (int j=temp2+1; j<this.Col-1; j++){
                                        if (this.Mtrx[temp][j] != 0){
                                            if (this.Mtrx[temp][j] < 0d) {
                                                writer.write(" + " + Math.abs((this.Mtrx[temp][j])) + param[j] + " ");
                                            }
                                            else {
                                                writer.write(" - " + (this.Mtrx[temp][j]) + param[j] + " ");
                                            }
                                        }
                                    } 
                                }
                                else{
                                    writer.write("X" + (temp2+1) + " = " + this.Mtrx[temp][this.Col-1]);
                                    for (int j=temp2+1; j<this.Col-1; j++){
                                        if (this.Mtrx[temp][j] != 0){
                                            if (this.Mtrx[temp][j] < 0d) {
                                                writer.write(" + " + Math.abs((this.Mtrx[temp][j])) + param[j] + " ");
                                            }
                                            else {
                                                writer.write(" - " + (this.Mtrx[temp][j]) + param[j] + " ");
                                            }
                                        }
                                    } 
                                }                       
                            }
                        }
                    }
                }
                else {
                    writer.write("SPL memiliki solusi unik");
                    writer.write("\n");
                    writer.write("Solusi adalah:");
                    writer.write(" ");
                    for (int i = 0; i < this.Row; i++){
                        writer.write("X" + (i+1) + " = ");
                        writer.write("" + this.Mtrx[i][this.Col-1]);
                        writer.write(" ");
                    }
                }
                writer.close();
                System.out.println("Berhasil dicetak di file");
            }
            catch (IOException e) {
                System.out.println("Ada error: ");
                e.printStackTrace();
            }
        }
        else{
            if (noSol) {
                System.out.printf("SPL tidak memiliki solusi");
            }
            else if (multSol) {
                System.out.printf("SPL memiliki banyak solusi");
                System.out.println();
                char [] param = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                for (int i=0; i < this.Col-1; i++){
                    System.out.printf("X%d = ", i+1);
                    System.out.printf("%c", param[i]);
                    System.out.println();
                }
                for (int i=0; i < this.Row; i++) {
                    if (this.Mtrx[i][i] == 1d) {
                        if (this.Mtrx[i][this.Col-1] != 0d){
                            System.out.printf("X%d = %.2f ", i+1, this.Mtrx[i][this.Col-1]);
                            for (int j=i+1; j<this.Col-1; j++){
                                if (this.Mtrx[i][j] != 0){
                                    if (this.Mtrx[i][j] < 0d) {
                                        System.out.printf("+ %.2f", -(this.Mtrx[i][j]));
                                        System.out.printf("%c ", param[j]);
                                    }
                                    else {
                                        System.out.printf("%.2f", -(this.Mtrx[i][j]));
                                        System.out.printf("%c ", param[j]);
                                    }
                                }
                            }                        
                        }
                        else {
                            System.out.printf("X%d = ", i+1);
                            for (int j=i+1; j<this.Col-1; j++){
                                if (this.Mtrx[i][j] != 0){
                                    System.out.printf("%.2f", -(this.Mtrx[i][j]));
                                    System.out.printf("%c ", param[j]);
                                }
                            }                        
                        }
                    }
                    else {
                        int temp = i;
                        int temp2 = i+1;
                        if (this.Mtrx[temp][this.Col-1] != 0d){
                            if(this.Mtrx[temp][temp2] == 0){
                                temp2 +=1;
                                System.out.printf("X%d = %.2f ", temp2+1, this.Mtrx[temp][this.Col-1]);
                                for (int j=temp2+1; j<this.Col-1; j++){
                                    if (this.Mtrx[temp][j] != 0){
                                        if (this.Mtrx[temp][j] < 0d) {
                                            System.out.printf("+ %.2f", -(this.Mtrx[temp][j]));
                                            System.out.printf("%c ", param[j]);
                                        }
                                        else {
                                            System.out.printf("%.2f", -(this.Mtrx[temp][j]));
                                            System.out.printf("%c ", param[j]);
                                        }
                                    }
                                } 
                            }
                            else{
                                System.out.printf("X%d = %.2f ", temp2+1, this.Mtrx[temp][this.Col-1]);
                                for (int j=temp2+1; j<this.Col-1; j++){
                                    if (this.Mtrx[temp][j] != 0){
                                        if (this.Mtrx[temp][j] < 0d) {
                                            System.out.printf("+ %.2f", -(this.Mtrx[temp][j]));
                                            System.out.printf("%c ", param[j]);
                                        }
                                        else {
                                            System.out.printf("%.2f", -(this.Mtrx[temp][j]));
                                            System.out.printf("%c ", param[j]);
                                        }
                                    }
                                } 
                            }                       
                        }
                    }
                }
            }
            else {
                System.out.printf("SPL memiliki solusi unik");
                System.out.println();
                System.out.printf("Solusi adalah:");
                System.out.println();
                for (int i = 0; i < this.Row; i++){
                    System.out.printf("X%d = ", i+1);
                    System.out.printf("%.2f", this.Mtrx[i][this.Col-1]);
                    System.out.println();
                }
            }
        }
    }
    
    public void findSPLwithInv(boolean toFile) {
        Matriks matPers = new Matriks();
        Matriks colHasil = new Matriks();
        double solution[] = new double[this.Row];
        int i, j;

        matPers.createMatriks(this.Row, this.Col - 1);
        colHasil.createMatriks(this.Row, 1);
        for (i = RowMin; i < this.Row; i++) {
            for (j = ColMin; j < this.Col - 1; j++) {
                matPers.Mtrx[i][j] = getElmt(i, j);
            }
        }
        for (i = RowMin; i < this.Row; i++) {
            colHasil.Mtrx[i][0] = getElmt(i, this.Col-1);
        }

        matPers.inversGaussWrite();
        if(matPers.hasInverse){
            for (i = RowMin; i < this.Row; i++) {
                for (j = ColMin; j < this.Col - 1; j++) {
                    solution[i] += matPers.Mtrx[i][j]*colHasil.Mtrx[j][0]; 
                }
            }
            //matPers.writeMatrix();
            //colHasil.writeMatrix();
            for (i = RowMin; i < this.Row; i++) {
                System.out.printf("x%d = %.5f \n", i + 1, solution[i]);
            }
    
            if (toFile) {
                try {
                    FileWriter writer = new FileWriter("outputSolusiSPL.txt");
                    for (i = RowMin; i < this.Row; i++) {
                        for (j = ColMin; j < this.Col; j++) {
                            writer.write(getElmt(i, j) + " ");
                        }
                        writer.write("\n");
                    }
                    writer.write("Solusi SPL di atas adalah:\n");
                    for (i = RowMin; i < this.Row; i++) {
                        writer.write("x" + (i+1) + " = " + solution[i] + " \n"); 
                    }
                    writer.close();
                    System.out.println("Berhasil dicetak di file");
                } 
                catch (IOException e) {
                    System.out.println("Ada error: ");
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.printf("Solusi tidak bisa dicari.");

            if(toFile){
                try{
                    FileWriter writer = new FileWriter("outputSolusiSPL.txt");
                    writer.write("Matriks tidak memiliki inverse, solusi tidak bisa dicari.");
                    writer.close();
                    System.out.println("\nBerhasil dicetak di file");
                }
                catch (IOException e){
                    System.out.println("Ada error: ");
                    e.printStackTrace();
                }
            }
        }

    }

    public void polynomInterpolate(boolean toFile) {
        double keepPoint[];
        keepPoint = new double[this.Row];
        int i, j;
        int zeroCol = 0;
        this.elimGaussJordan();
        for (i = RowMin; i < this.Row; i++) {
            for (j = i + zeroCol; j < this.Col - 1; j++) {
                if (getElmt(i, j) == 1) {
                    keepPoint[j] = getElmt(i, this.Col-1);
                    break;
                }
                else {
                    zeroCol++;
                }
            }
        }
        System.out.println("Persamaan dari interpolasi adalah");
        System.out.printf("P(x) = %.5f ", keepPoint[0]);
        for (i = 1; i < this.Col - 1; i++) {
            if (keepPoint[i] > 0) {
                System.out.printf("+ %.5fx^%d ", keepPoint[i], i);
            }
            if (keepPoint[i] < 0) {
                System.out.printf("+ (%.5fx^%d) ", keepPoint[i], i);
            }
        }
        double find;
        System.out.printf("\nNilai yang ingin ditaksir: ");
        find = scanner.nextDouble();

        double result = 0;
        for (i = 0; i < this.Col - 1; i++) {
            result += keepPoint[i] * Math.pow(find, i);
        }
        System.out.printf("P(%.5f) = %.5f\n", find, result);

        if (toFile) {
            try {
                FileWriter writer = new FileWriter("outputInterpolate.txt");
                writer.write("Persamaan dari interpolasi adalah\n");
                writer.write("P(x) = " + keepPoint[0] + " ");
                for (i = 1; i < this.Col - 1; i++) {
                    if (keepPoint[i] > 0) {
                        writer.write("+ " + keepPoint[i] + "x^" + i + " ");
                    }
                    if (keepPoint[i] < 0) {
                        writer.write("+ (" + keepPoint[i] + "x^" + i + ") ");
                    }
                }
                writer.write("\nP(" + find + ") = " + result);
                writer.close();
                System.out.println("\nBerhasil dicetak di file");
            } catch (IOException e) {
                System.out.println("Ada error: ");
                e.printStackTrace();
            }
        }
    }

    void double_regression(int n_factor, double[] est, boolean toFile){
        Matriks m = new Matriks();
        m.createMatriks(this.Col, this.Col+1);
        int row, col;
        for(row = 0; row < m.Row; row++){
            for(col = 0; col < m.Col; col++){
                if(row == 0 && col == 0){
                    m.Mtrx[row][col] = this.Row;
                }
                else if((row == 0 && col != 0)){
                    m.Mtrx[row][col] = 0;
                    int sec_row;
                    for(sec_row = 0; sec_row < this.Row; sec_row++){
                        m.Mtrx[row][col] += this.Mtrx[sec_row][col-1];
                    }
                }
                else if((row != 0 && col == 0)){
                    m.Mtrx[row][col] = 0;
                    int sec_row;
                    for(sec_row = 0; sec_row < this.Row; sec_row++){
                        m.Mtrx[row][col] += this.Mtrx[sec_row][row-1];
                    }
                }
                else{
                    m.Mtrx[row][col] = 0;
                    int sec_row;
                    for(sec_row = 0; sec_row < this.Row; sec_row++){
                        m.Mtrx[row][col] += this.Mtrx[sec_row][row-1] * this.Mtrx[sec_row][col-1];
                    }
                }

            }
        }
        m.elimGaussJordan();
        if(toFile == false){
            System.out.printf("\nPersamaan Regresi Linear Berganda dari data yang diberikan adalah: ");
            System.out.printf("Y = %6.6f ", m.Mtrx[0][m.Col-1]);
            int i;
            for (i = 1; i < m.Row ; i++) {
                if (m.Mtrx[i][m.Col-1] < 0) {
                    System.out.printf("- %6.6fx^%d ", Math.abs(m.Mtrx[i][m.Col-1]), i);
                } 
                else if (m.Mtrx[i][m.Col-1] > 0) {
                    System.out.printf("+ %6.6fx^%d ", m.Mtrx[i][m.Col-1], i);
                }
            }
            System.out.println();
            System.out.println("\nSehingga nilai ");
            for (i = 0; i < m.Row ; i++) {
                if (m.Mtrx[i][m.Col-1] < 0) {
                    System.out.printf("b%d = - %6.6f ",i, Math.abs(m.Mtrx[i][m.Col-1]));
                } 
                else if (m.Mtrx[i][m.Col-1] > 0) {
                    System.out.printf("b%d = %6.6f ",i, m.Mtrx[i][m.Col-1]);
                }
            }
            double y;
            y = m.Mtrx[0][m.Col-1];
            for(i = 1; i <= n_factor; i++){
                y += m.Mtrx[i][m.Col-1] * est[i-1];
            }
            System.out.print("\n\n, dan taksiran atau estimasi dari nilai yang diberikan adalah adalah: ");
            System.out.printf(" Y = %6.6f", y);
        }
        else if(toFile){
            try {
                FileWriter writer = new FileWriter("outputdouble_regression.txt");
                writer.write("Persamaan Regresi Linear Berganda dari data adalah : ");
                writer.write("Y = " + m.Mtrx[0][m.Col-1]);
                int i;
                for (i = 1; i < m.Row ; i++) {
                    if (m.Mtrx[i][m.Col-1] < 0) {
                        writer.write(" - "+ Math.abs(m.Mtrx[i][m.Col-1]) +"x^" + i);
                    } 
                    else if (m.Mtrx[i][m.Col-1] > 0) {
                        writer.write(" + "+ m.Mtrx[i][m.Col-1]+"x^" + i);
                    }
                }
                writer.write(" ");
                writer.write("\nSehingga nilai ");
                for (i = 0; i < m.Row ; i++) {
                    if (m.Mtrx[i][m.Col-1] < 0) {
                        writer.write("b"+i +" = -" + Math.abs(m.Mtrx[i][m.Col-1]) + " ");
                    } 
                    else if (m.Mtrx[i][m.Col-1] > 0) {
                        writer.write("b"+i + " = " + m.Mtrx[i][m.Col-1]+ " ");
                    }
                }
                writer.write("");
                double y;
                y = m.Mtrx[0][m.Col-1];
                for(i = 1; i <= n_factor; i++){
                    y += m.Mtrx[i][m.Col-1] * est[i-1];
                }
                writer.write("\n\n, dan taksiran atau estimasi dari nilai yang diberikan adalah adalah: ");
                writer.write(" Y = "+ y);
                writer.close();
                System.out.println("\nBerhasil dicetak di file");
            } catch (IOException e) {
                System.out.println("Ada error: ");
                e.printStackTrace();
            }
        }
    }
}