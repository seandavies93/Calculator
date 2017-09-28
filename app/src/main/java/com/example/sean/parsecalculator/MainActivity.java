package com.example.sean.parsecalculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text;
    TextView answer;

    private String strinput="";

    public boolean bracepresent(String s)
    {
        int count=0;
        for (int i=0 ; i<s.length() ; i++)
        {
            if((s.charAt(i)=='(')||(s.charAt(i)==')'))
            {
                count++;
            }
        }
        if(count!=0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
    //this method descends to the lowest bracews in the string and evaluates the expression within it, subsequently replacing it with the result
    public String parsebrack(String s)
    {
        int low=0;
        int high=0;
        for (int i=0 ; i<s.length() ; i++)
        {
            if(s.charAt(i)=='(')
            {
                low=i;
            }
            else if(s.charAt(i)==')')
            {
                high=i;
                break;
            }
        }
        if(bracepresent(s))
        {
            String termgroup = s.substring(low + 1, high);
            String termgroupred = String.valueOf(parseadd(parsesub(parsemult(parsediv(parsenum(termgroup))))));
            String reduction = s.substring(0, low) + termgroupred + s.substring(high + 1, s.length());
            if (bracepresent(reduction))
            {
                return parsebrack(reduction);
            }
            else
            {
                return reduction;
            }
        }
        else
        {
            return s;
        }
    }
    //splits up the string into an array of separate strings, each either denoting a number or an operator
    public String[] parsenum(String s)
    {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        toast.show();
        int size=0;
        for (int j=0 ; j<s.length() ; j++)
        {
            if((s.charAt(j)=='+')||(s.charAt(j)=='×')||(s.charAt(j)=='÷')||(s.charAt(j)=='-'))
            {
                size++;
            }
        }
        int siz=2*size+1;
        String[] code=new String[siz];
        int i=0;
        int k=0;
        String intermediate="";
        while(k<siz)
        {
            if(i==s.length())
            {
                code[k]=intermediate;
                break;
            }
            if((s.charAt(i) != '+')&&(s.charAt(i) != '×')&&(s.charAt(i)!='÷')&&(s.charAt(i)!='-'))
            {
                intermediate += String.valueOf(s.charAt(i));
            }
            else
            {
                code[k] = intermediate;
                k++;
                code[k]= String.valueOf(s.charAt(i));
                k++;
                intermediate = "";
            }
            i++;
        }
        return code;
    }
    public String[] parsediv(String[] s)
    {
        String[] result;
        int i=0;
        int j=0;
        while(i<(s.length))
        {
            if(s[i].equals("÷"))
            {
                {
                    break;
                }
            }
            i++;
        }
        if(i!=s.length) {
            result = new String[s.length - 2];
            while (j < s.length - 2)
            {
                if (j < i - 1)
                {
                    result[j] = s[j];
                }
                else if (j == i - 1)
                {
                    result[j] = String.valueOf(Double.parseDouble(s[i - 1]) / Double.parseDouble(s[i + 1]));
                }
                else if (j > i - 1)
                {
                    result[j] = s[j + 2];
                }
                j++;
            }
            int multsum = 0;
            for (int l = 0; l < result.length; l++)
            {
                if (result[l].equals("÷"))
                {
                    multsum++;
                }
            }
            if (multsum != 0)
            {
                result = parsediv(result);
            }
        }
        else
        {
            result = s;
        }
        return result;
    }
    public String[] parsemult(String[] s)
    {
        String[] result;
        int i=0;
        int j=0;
        while(i<(s.length))
        {
            if(s[i].equals("×"))
            {
                {
                    break;
                }
            }
            i++;
        }
        if(i!=s.length)
        {
            result = new String[s.length - 2];

            while (j < s.length - 2) {
                if (j < i - 1)
                {
                    result[j] = s[j];
                }
                else if (j == i - 1)
                {
                    result[j] = String.valueOf(Double.parseDouble(s[i - 1]) * Double.parseDouble(s[i + 1]));
                }
                else if (j > i - 1)
                {
                    result[j] = s[j + 2];
                }
                j++;
            }
            int multsum = 0;
            for (int l = 0; l < result.length; l++)
            {
                if (result[l].equals("×"))
                {
                    multsum++;
                }
            }
            if (multsum != 0)
            {
                result = parsemult(result);
            }
        }
        else
        {
            result = s;
        }
        return result;
    }
    public String[] parsesub(String[] s)
    {
        String[] result;
        int i=0;
        int j=0;
        while(i<(s.length))
        {
            if(s[i].equals("-"))
            {
                {
                    break;
                }
            }
            i++;
        }
        if(i!=s.length)
        {
            result = new String[s.length - 2];
            while (j < s.length - 2)
            {
                if (j < i - 1)
                {
                    result[j] = s[j];
                }
                else if (j == i - 1)
                {
                    result[j] = String.valueOf(Double.parseDouble(s[i - 1]) - Double.parseDouble(s[i + 1]));
                }
                else if (j > i - 1)
                {
                    result[j] = s[j + 2];
                }
                j++;
            }
            int multsum = 0;
            for (int l = 0; l < result.length; l++)
            {
                if (result[l].equals("-"))
                {
                    multsum++;
                }
            }
            if (multsum != 0)
            {
                return parsesub(result);
            }
        }
        else
        {
            return s;
        }
        return result;
    }
    public Double parseadd(String[] s)
    {
        Double result=0.0;
        int j=0;
        while (j<s.length)
        {

            if (!(s[j].equals("+")))
            {
                result+=Double.parseDouble(s[j]);
            }
            j++;
        }
        return result;
    }
    public boolean isValid(String word)
    {
        if(word==null)
        {
            return false;
        }
        for(int i=0; i<word.length(); i++)
        {
            char current=word.charAt(i);
            if(!((current=='0')
                    ||(current=='1')
                    ||(current=='2')
                    ||(current=='3')
                    ||(current=='4')
                    ||(current=='5')
                    ||(current=='6')
                    ||(current=='7')
                    ||(current=='8')
                    ||(current=='9')
                    ||(current=='(')
                    ||(current==')')
                    ||(current=='+')
                    ||(current=='×')
                    ||(current=='-')
                    ||(current=='÷')))
            {
                return false;
            }
        }
        return true;
    }
    public void calculate(View view)
    {
        text=(EditText) findViewById(R.id.text);
        answer=(TextView) findViewById(R.id.ans);
        strinput=text.getText().toString();
        if(isValid(strinput))
        {
            answer.setText(String.valueOf(parseadd(parsesub(parsemult(parsediv(parsenum(parsebrack(strinput))))))));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
