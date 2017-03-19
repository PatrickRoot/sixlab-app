package cn.sixlab.app.sixlabapp.exp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sixlab.app.sixlabapp.R;

public class ExpActivity extends AppCompatActivity {

    @BindView(R.id.exp_input)
    EditText input;

    @BindView(R.id.exp_btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);

        ButterKnife.bind(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if(null!=text&&!"".equals(text)){
                    switch (text){
                        case "1":
                            // Intent intent = new Intent();
                            // intent.setClass(ExpActivity.this, Sample1Activity.class);
                            // ExpActivity.this.startActivity(intent);
                            break;
                    }
                }
            }
        });
    }
}
