package com.shine.ui.text;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.shine.ui.BaseActivity;
import com.shine.ui.R;

/**
 * //        //文本类型，多为大写、小写和数字符号。
 * //        android:inputType="none"//输入普通字符
 * //        android:inputType="text"//输入普通字符
 * //        android:inputType="textCapCharacters"//输入普通字符
 * //        android:inputType="textCapWords"//单词首字母大小
 * //        android:inputType="textCapSentences"//仅第一个字母大小
 * //        android:inputType="textAutoCorrect"//前两个自动完成
 * //        android:inputType="textAutoComplete"//前两个自动完成
 * //        android:inputType="textMultiLine"//多行输入
 * //        android:inputType="textImeMultiLine"//输入法多行（不一定支持）
 * //        android:inputType="textNoSuggestions"//不提示
 * //        android:inputType="textUri"//URI格式
 * //        android:inputType="textEmailAddress"//电子邮件地址格式
 * //        android:inputType="textEmailSubject"//邮件主题格式
 * //        android:inputType="textShortMessage"//短消息格式
 * //        android:inputType="textLongMessage"//长消息格式
 * //        android:inputType="textPersonName"//人名格式
 * //        android:inputType="textPostalAddress"//邮政格式
 * //        android:inputType="textPassword"//密码格式
 * //        android:inputType="textVisiblePassword"//密码可见格式
 * //        android:inputType="textWebEditText"//作为网页表单的文本格式
 * //        android:inputType="textFilter"//文本筛选格式
 * //        android:inputType="textPhonetic"//拼音输入格式
 * //        //数值类型
 * //        android:inputType="number"//数字格式
 * //        android:inputType="numberSigned"//有符号数字格式
 * //        android:inputType="numberDecimal"//可以带小数点的浮点格式
 * //        android:inputType="phone"//拨号键盘
 * //        android:inputType="datetime"//日期+时间格式
 * //        android:inputType="date"//日期键盘
 * //        android:inputType="time"//时间键盘
 */
public class TextViewActivity extends BaseActivity {
    private TextView mTextView;
    private CheckedTextView mCheckedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        init();
    }

    private void init() {
        mTextView = findViewById(R.id.textView);
        EditText mEditText = findViewById(R.id.editText);
        AutoCompleteTextView mAutoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        MultiAutoCompleteTextView mMultiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView);
        mCheckedTextView = findViewById(R.id.checkedTextView);


        mEditText.addTextChangedListener(textWatcher);
        mCheckedTextView.setCheckMarkDrawable(R.drawable.ic_check_text);
        mCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printLog("mCheckedTextView...isChecked==" + mCheckedTextView.isChecked());
                mCheckedTextView.setChecked(!mCheckedTextView.isChecked());
            }
        });

        String[] data = new String[]{"123", "123456", "1234567", "123456789"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, data);
        mAutoCompleteTextView.setAdapter(arrayAdapter);

        MultiAutoCompleteTextView.Tokenizer tokenizer = new SemicolonTokenizer(' ');
        mMultiAutoCompleteTextView.setAdapter(arrayAdapter);
        mMultiAutoCompleteTextView.setTokenizer(tokenizer);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mTextView.setText(s.toString());
        }
    };

    private class SemicolonTokenizer implements MultiAutoCompleteTextView.Tokenizer {
        private char charS;
        private String mString;

        private SemicolonTokenizer(char charS) {
            this.charS = charS;
            mString = String.valueOf(charS);
        }

        public int findTokenStart(CharSequence text, int cursor) {
            int i = cursor;
            while (i > 0 && text.charAt(i - 1) != charS) {
                i--;
            }
            while (i < cursor && text.charAt(i) == ' ') {
                i++;
            }

            return i;
        }

        public int findTokenEnd(CharSequence text, int cursor) {
            int i = cursor;
            int len = text.length();

            while (i < len) {
                if (text.charAt(i) == charS) {
                    return i;
                } else {
                    i++;
                }
            }

            return len;
        }

        public CharSequence terminateToken(CharSequence text) {
            int i = text.length();
            while (i > 0 && text.charAt(i - 1) == ' ') {
                i--;
            }

            if (i > 0 && text.charAt(i - 1) == charS) {
                return text;
            } else {
                if (text instanceof Spanned) {
                    SpannableString sp = new SpannableString(text + mString);
                    TextUtils.copySpansFrom((Spanned) text, 0, text.length(), Object.class, sp, 0);
                    return sp;
                } else {
                    return text + mString;
                }
            }
        }
    }


}
