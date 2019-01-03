package com.example.yrchoi.yurist.NotificationService;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yrchoi.yurist.R;

import static com.example.yrchoi.yurist.util.KeyboardUtils.showKeyboard;
import static com.example.yrchoi.yurist.util.Utils.getSHA256String;

/**
 * Created by neozen on 2018-04-04.
 */

public class LoginFragment extends Fragment {

    private Button btn_login;
    private EditText et_id;
    private EditText et_pw;

    public static int LOGIN_OK = 0;
    private static int LOGIN_NO_MATCH = 3;
    public static final int INDEX_GET_LOGIN_INFO = 2;
    private static int LOGIN_FAIL_CONNECT = 5;

    private FCMActivity fcmActivity;
    private int login_result = 999;
    public static int login_check;

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.fragment_login_main, container, false);

        btn_login = loginView.findViewById(R.id.btn_login);
        et_id = loginView.findViewById(R.id.et_id);
        et_pw = loginView.findViewById(R.id.et_pw);

        fcmActivity = (FCMActivity) getActivity();

        et_id.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_NEXT:
                        et_pw.requestFocus();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        et_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKeyboard(getActivity(), et_id);
            }
        });

        final View view = loginView;

        et_pw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        if ((et_id.getText().toString().length() > 0) && (et_pw.getText().toString().length() > 0)) {
                            String id = et_id.getText().toString();
                            String pw = et_pw.getText().toString();

                            int login_result = check_Login_Information(getActivity(), id, pw);

                            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

                            // 로그인 성공
                            if (login_result == LOGIN_OK) {
                                showFragmentMain();
                            } else {

                            }
                        }
                        break;
                }

                return false;
            }
        });

        et_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKeyboard(getActivity(), et_pw);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((et_id.getText().toString().length() > 0) && (et_pw.getText().toString().length() > 0)) {
                    String id = et_id.getText().toString();
                    String pw = et_pw.getText().toString();

                    int login_result = check_Login_Information(getActivity(), id, pw);

                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

                    // 로그인 성공
                    if (login_result == LOGIN_OK) {
                        showFragmentMain();

                        login_check = login_result;
                    } else {
                        // 로그인 실패
                        Toast.makeText(getActivity(), "로그인 실패!", Toast.LENGTH_SHORT).show();

                        et_id.setText("");
                        et_pw.setText("");
                    }
                }
            }
        });


        return loginView;
    }

    private int check_Login_Information(FragmentActivity activity, String id, String pw) {
        String encrypt_pw = new String();

        try {
            encrypt_pw = getSHA256String(pw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boolean loginInfo = getLoginInfo(INDEX_GET_LOGIN_INFO, activity, id, pw);

        if (!loginInfo) {
            return LoginFragment.LOGIN_NO_MATCH;
        } else {
            return LoginFragment.LOGIN_OK;
        }
    }

    private boolean getLoginInfo(int index, Context context, String user_id, String user_pw) {
        if (user_id.equals("1") && user_pw.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    private void showFragmentMain() {
        FragmentMain fragmentSet = new FragmentMain();

        fcmActivity.onFragmentChanged(fcmActivity.FRAGMENT_MAIN, fragmentSet, FCMActivity.BACK_TYPE_NORMAL);
    }
}
