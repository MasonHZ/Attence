package com.example.a89370;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalcuterActivity extends AppCompatActivity {

    private EditText result;
    private Button deleteAll;
    private Button delete;
    private Button div;
    private Button mul;
    private Button add;
    private Button sub;
    private Button Numb0;
    private Button Numb1;
    private Button Numb2;
    private Button Numb3;
    private Button Numb4;
    private Button Numb5;
    private Button Numb6;
    private Button Numb7;
    private Button Numb8;
    private Button Numb9;
    private Button point;
    private Button getResult;

    private String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcuter);

        int c = 1;
        double d = 9.0;
        double e = c * d;

        result = (EditText) findViewById(R.id.result);

        deleteAll = (Button) findViewById(R.id.deleteAll);
        delete = (Button) findViewById(R.id.delete);

        add = (Button) findViewById(R.id.addition);
        mul = (Button) findViewById(R.id.multiplication);
        sub = (Button) findViewById(R.id.subtraction);
        div = (Button) findViewById(R.id.division);

        Numb0 = (Button) findViewById(R.id.Numb0);
        Numb1 = (Button) findViewById(R.id.Numb1);
        Numb2 = (Button) findViewById(R.id.Numb2);
        Numb3 = (Button) findViewById(R.id.Numb3);
        Numb4 = (Button) findViewById(R.id.Numb4);
        Numb5 = (Button) findViewById(R.id.Numb5);
        Numb6 = (Button) findViewById(R.id.Numb6);
        Numb7 = (Button) findViewById(R.id.Numb7);
        Numb8 = (Button) findViewById(R.id.Numb8);
        Numb9 = (Button) findViewById(R.id.Numb9);
        point = (Button) findViewById(R.id.point);

        getResult = (Button) findViewById(R.id.getResult);

        deleteAll.setOnClickListener(new ButtonListener());
        delete.setOnClickListener(new ButtonListener());
        Numb0.setOnClickListener(new ButtonListener());
        Numb1.setOnClickListener(new ButtonListener());
        Numb2.setOnClickListener(new ButtonListener());
        Numb3.setOnClickListener(new ButtonListener());
        Numb4.setOnClickListener(new ButtonListener());
        Numb5.setOnClickListener(new ButtonListener());
        Numb6.setOnClickListener(new ButtonListener());
        Numb7.setOnClickListener(new ButtonListener());
        Numb8.setOnClickListener(new ButtonListener());
        Numb9.setOnClickListener(new ButtonListener());
        point.setOnClickListener(new ButtonListener());

        mul.setOnClickListener(new ButtonListener());
        add.setOnClickListener(new ButtonListener());
        div.setOnClickListener(new ButtonListener());
        sub.setOnClickListener(new ButtonListener());

        getResult.setOnClickListener(new ButtonListener());

    }

    class ButtonListener implements View.OnClickListener {//点击事件监听器

        @Override
        public void onClick(View v) {
            str = result.getText().toString();
            switch (v.getId()) {
                case R.id.Numb1:
                case R.id.Numb2:
                case R.id.Numb3:
                case R.id.Numb4:
                case R.id.Numb5:
                case R.id.Numb6:
                case R.id.Numb7:
                case R.id.Numb8:
                case R.id.Numb9:
                case R.id.Numb0:
                    result.setText(str + ((Button) v).getText());//0-9的数字直接输入，并跳出
                    break;
                case R.id.point:
                    if (str == null || str.equals("")) {//第一位不能放 .
                        result.setText("");
                        break;
                    }
                    if (str.substring(str.length() - 1).equals("*") ||
                            str.substring(str.length() - 1).equals("/") ||
                            str.substring(str.length() - 1).equals("+") ||
                            str.substring(str.length() - 1).equals("-") ||
                            str.substring(str.length() - 1).equals(".")) {//四则运算符后不能放 .   并直接跳出
                        break;
                    }
                    if (str.lastIndexOf(".") > str.indexOf(".")) {//存在两个 . 时，直接跳出
                        break;
                    }
                    if (str.lastIndexOf(".") == str.indexOf(".") && str.indexOf(".") > 0) {//当存在只一个 . 时    （后面的条件主要是因为不存在  . 时indexOf值都为-1）
                        if (!choice()) {//不包含符号时，不能继续放  .   并直接跳出
                            break;
                        }
                        if (choice() && str.substring(str.indexOf("+") + 1).contains(".")
                                && str.substring(str.indexOf("*") + 1).contains(".")
                                && str.substring(str.lastIndexOf("-") + 1).contains(".")
                                && str.substring(str.indexOf("/") + 1).contains(".")) {//包含运算符号，并且运算符号以后的数字已经有  .  时，直接跳出
                            break;
                        }
                    }
                    result.setText(str + ((Button) v).getText());//其他情况直接放  .   然后跳出
                    break;


                case R.id.delete://---------------------回退，并跳出
                    if (str.length() >= 1)//可编辑栏长度大于1，直接回退
                        result.setText(str.substring(0, str.length() - 1));
                    else result.setText("");//可编辑栏小于或等于1，直接制空
                    break;
                case R.id.deleteAll://------------------删除所有，即制空后跳出
                    result.setText("");
                    break;
                case R.id.multiplication://---------------------------乘号
                    if (str == null || str.equals("")) {//当编辑栏为空时，直接跳出
                        result.setText("");
                        break;
                    }
                    if (str.substring(str.length() - 1).equals(".")) {//当末位是  .  时，直接跳出
                        result.setText(str);
                        break;
                    }
                    if (choice()) {//已存在运算符时
                        if (str.substring(str.length() - 1).equals("*") ||
                                str.substring(str.length() - 1).equals("/") ||
                                str.substring(str.length() - 1).equals("+") ||
                                str.substring(str.length() - 1).equals("-")
                                ) {//末尾是运算符的情况
                            if (str.length() > 1) {//如果可编辑栏长度大于1（即排除首位为负号的情况），将末位置换为 *  后跳出
                                result.setText(str.substring(0, str.length() - 1) + "*");
                                break;
                            } else {//否则直接跳出
                                break;
                            }
                        } else {//末尾不是运算符时
                            test();//进行运算
                            result.setText(str + ((Button) v).getText());//再加上  *  并跳出
                        }
                    } else {//不存在运算符时，加上  *   后跳出
                        result.setText(str + ((Button) v).getText());
                    }
                    break;
                case R.id.addition://--------------------------------加法（与乘法相似）
                    if (str == null || str.equals("")) {
                        result.setText("");
                        break;
                    }
                    if (str.substring(str.length() - 1).equals(".")) {
                        result.setText(str);
                        break;
                    }
                    if (choice()) {
                        if (str.substring(str.length() - 1).equals("*") ||
                                str.substring(str.length() - 1).equals("/") ||
                                str.substring(str.length() - 1).equals("+") ||
                                str.substring(str.length() - 1).equals("-")
                                ) {
                            if (str.length() > 1) {
                                result.setText(str.substring(0, str.length() - 1) + "+");
                                break;
                            } else {
                                break;
                            }
                        } else {
                            test();
                            result.setText(str + ((Button) v).getText());
                        }
                    } else {
                        result.setText(str + ((Button) v).getText());
                    }
                    break;
                case R.id.division://---------------------------------除法（与乘法相似）
                    if (str == null || str.equals("")) {
                        result.setText("");
                        break;
                    }
                    if (str.substring(str.length() - 1).equals(".")) {
                        result.setText(str);
                        break;
                    }
                    if (choice()) {
                        if (str.substring(str.length() - 1).equals("*") ||
                                str.substring(str.length() - 1).equals("/") ||
                                str.substring(str.length() - 1).equals("+") ||
                                str.substring(str.length() - 1).equals("-")
                                ) {
                            if (str.length() > 1) {
                                result.setText(str.substring(0, str.length() - 1) + "/");
                                break;
                            } else {
                                break;
                            }
                        } else {
                            test();
                            result.setText(str + ((Button) v).getText());
                        }
                    } else {
                        result.setText(str + ((Button) v).getText());
                    }
                    break;
                case R.id.subtraction://--------------------------------减法（部分与乘法相似，相差较大部分做出标识）
                    if (str == null || str.equals("")) {//可编辑栏为空时，加上  -   （避免使用不了负数）
                        result.setText("-");
                        break;
                    }
                    if (str.substring(str.length() - 1).equals(".")) {
                        result.setText(str);
                        break;
                    }
                    if (choice()) {
                        if (str.substring(str.length() - 1).equals("*") ||
                                str.substring(str.length() - 1).equals("/") ||
                                str.substring(str.length() - 1).equals("+") ||
                                str.substring(str.length() - 1).equals("-")
                                ) {
                            result.setText(str.substring(0, str.length() - 1) + "-");
                            break;
                        } else {
                            test();
                            result.setText(str + ((Button) v).getText());
                            break;
                        }
                    } else {
                        result.setText(str + ((Button) v).getText());
                    }
                    break;
                case R.id.getResult:
                    if (str == null || str.equals("")) {
                        result.setText("");
                        break;
                    }
                    if (str.substring(str.length() - 1).equals("*") ||
                            str.substring(str.length() - 1).equals("/") ||
                            str.substring(str.length() - 1).equals("+") ||
                            str.substring(str.length() - 1).equals("-") ||
                            str.substring(str.length() - 1).equals(".")
                            ) {
                        result.setText(str);
                        break;
                    } else {
                        test();
                        break;
                    }
                default:
            }

        }
    }

    private void test() {
        if (str.contains("Infinity")) {
            result.setText("");
            return;
        } else if (str.contains("*")) {
            double a = Double.parseDouble(str.substring(0, str.indexOf("*")));
            double b = Double.parseDouble(str.substring(str.indexOf("*") + 1));
            str = a * b + "";
            result.setText(str);
            return;
        } else if (str.contains("/")) {
            double a = Double.parseDouble(str.substring(0, str.indexOf("/")));
            double b = Double.parseDouble(str.substring(str.indexOf("/") + 1));
            str = a / b + "";
            if (str.equals("Infinity")) {
                result.setText("");
                return;
            }
            result.setText(str);
            return;
        } else if (str.contains("+")) {
            double a = Double.parseDouble(str.substring(0, str.indexOf("+")));
            double b = Double.parseDouble(str.substring(str.indexOf("+") + 1));
            str = a + b + "";
            result.setText(str);
            return;
        }
        if (str.contains("-")) {
            if (str.indexOf("-") > 0) {//存在 -  但是为减号时
                double a = Double.parseDouble(str.substring(0, str.lastIndexOf("-")));
                double b = Double.parseDouble(str.substring(str.lastIndexOf("-") + 1));
                str = a - b + "";
                result.setText(str);
            } else if (str.indexOf("-") == 0 && str.lastIndexOf("-") > 0) {//同时存在负数和减号时
                double a = Double.parseDouble(str.substring(0, str.lastIndexOf("-")));
                double b = Double.parseDouble(str.substring(str.lastIndexOf("-") + 1));
                str = a - b + "";
                result.setText(str);
            } else if (str.lastIndexOf("-") == 0) {//只存在负号时
                return;
            }
        }
    }

    private boolean choice() {
//        String string = result.toString();
        if ((str.substring(0, str.length())).contains("+")
                || (str.substring(0, str.length())).contains("-")
                || (str.substring(0, str.length())).contains("*")
                || (str.substring(0, str.length())).contains("/")) {
            return true;
        } else return false;
    }

}
