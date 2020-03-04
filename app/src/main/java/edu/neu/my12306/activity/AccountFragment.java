package edu.neu.my12306.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my12306.R;
import edu.neu.my12306.database.UserDao;
import edu.neu.my12306.entity.Account;

public class AccountFragment extends Fragment {
    private TextView tv_username;
    private TextView tv_name;
    private TextView tv_certificateType;
    private TextView tv_certificateNumber ;
    private TextView tv_passengerType;
    private TextView tv_phoneNumber ;
    private Button bt_save;
    private int selectedId = -1;
    private View view;
    private UserDao userDao;
    private Account account;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account,container,false);
        tv_username= view.findViewById(R.id.username);
        tv_name = view.findViewById(R.id.name);
        tv_certificateType=view.findViewById(R.id.certificateType);
        tv_certificateNumber = view.findViewById(R.id.certificateNumber);
        tv_passengerType = view.findViewById(R.id.passengerType);
        tv_phoneNumber = view.findViewById(R.id.phoneNumber);
        bt_save = view.findViewById(R.id.saveMessage);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userDao  = new UserDao(getContext());
        account = Account.getAccount();
        tv_username.setText(account.getUsername());
        tv_name.setText(account.getName());
        tv_certificateType.setText(account.getCertificateType());
        tv_certificateNumber.setText(account.getCertificateNumber());
        tv_passengerType.setText(account.getPassengerType());
        tv_phoneNumber.setText(account.getPhoneNumber());
        tv_username.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText editText = new EditText(getContext());
                AlertDialog.Builder editDialog = new AlertDialog.Builder(getContext());
                editDialog.setIcon(R.drawable.logo);
                editDialog.setTitle("编辑用户名").setView(editText);
                editDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_username.setText(editText.getText().toString());

                    }
                });
                editDialog.show();
            }
        });
        tv_name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText editText = new EditText(getContext());
                AlertDialog.Builder editDialog = new AlertDialog.Builder(getContext());
                editDialog.setIcon(R.drawable.logo);
                editDialog.setTitle("编辑姓名").setView(editText);
                editDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_name.setText(editText.getText().toString());
                    }
                });
                editDialog.show();
            }
        });

        tv_certificateType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String[] certificateType={"身份证","港澳台通行证","退伍军人证"};
                new AlertDialog.Builder(getContext())
                        .setIcon(R.drawable.logo)
                        .setTitle("请选择证件类型")
                        .setSingleChoiceItems(certificateType, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedId = which;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv_certificateType.setText(certificateType[selectedId]);
                                selectedId = -1;

                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();

            }
        });
        tv_certificateNumber.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText editText = new EditText(getContext());
                AlertDialog.Builder editDialog = new AlertDialog.Builder(getContext());
                editDialog.setIcon(R.drawable.logo);
                editDialog.setTitle("编辑证件号码").setView(editText);
                editDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_certificateNumber.setText(editText.getText().toString());
                    }
                });
                editDialog.show();
            }
        });
        tv_passengerType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String[] passengerType={"普通乘客","学生","退伍军人"};
                new AlertDialog.Builder(getContext())
                        .setIcon(R.drawable.logo)
                        .setTitle("请选择乘客类型")
                        .setSingleChoiceItems(passengerType, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedId = which;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv_passengerType.setText(passengerType[selectedId]);
                                selectedId = -1;
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();

            }
        });
        tv_phoneNumber.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText editText = new EditText(getContext());
                AlertDialog.Builder editDialog = new AlertDialog.Builder(getContext());
                editDialog.setIcon(R.drawable.logo);
                editDialog.setTitle("编辑电话号码").setView(editText);
                editDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_phoneNumber.setText(editText.getText().toString());
                    }
                });
                editDialog.show();
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setUsername(tv_username.getText().toString());
                account.setName(tv_name.getText().toString());
                account.setCertificateType(tv_certificateType.getText().toString());
                account.setCertificateNumber(tv_certificateNumber.getText().toString());
                account.setPassengerType(tv_passengerType.getText().toString());
                account.setPhoneNumber(tv_phoneNumber.getText().toString());
                userDao.changeAccount(account);
                Toast.makeText(getContext(), "成功修改信息", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
