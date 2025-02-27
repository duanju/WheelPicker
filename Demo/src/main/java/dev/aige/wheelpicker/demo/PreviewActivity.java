package dev.aige.wheelpicker.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import dev.aige.wheelpicker.WheelPicker;
import dev.aige.wheelpicker.demo.R;

/**
 * @author AigeStudio 2015-12-06
 * @author AigeStudio 2016-07-08
 */
public class PreviewActivity extends Activity implements WheelPicker.OnItemSelectedListener, View.OnClickListener {

    private WheelPicker wheelLeft;
    private WheelPicker wheelCenter;
    private WheelPicker wheelRight;

    private Button gotoBtn;
    private Integer gotoBtnItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_preview);

        wheelLeft = (WheelPicker) findViewById(R.id.main_wheel_left);
        wheelLeft.setOnItemSelectedListener(this);
        wheelCenter = (WheelPicker) findViewById(R.id.main_wheel_center);
        wheelCenter.setOnItemSelectedListener(this);
        wheelRight = (WheelPicker) findViewById(R.id.main_wheel_right);
        wheelRight.setOnItemSelectedListener(this);

        gotoBtn = (Button) findViewById(R.id.goto_btn);
        randomlySetGotoBtnIndex();
        gotoBtn.setOnClickListener(this);
    }

    private void randomlySetGotoBtnIndex() {
        gotoBtnItemIndex = (int) (Math.random() * wheelCenter.getData().size());
        gotoBtn.setText("Goto '" + wheelCenter.getData().get(gotoBtnItemIndex) + "'");
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        int target = picker.getId();
        if (target == R.id.main_wheel_center) {
            text = "Center:";
        } else if (target == R.id.main_wheel_right) {
            text = "Right:";
        } else {
            text = "Left";
        }

        Toast.makeText(this, text + String.valueOf(data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        wheelCenter.setSelectedItemPosition(gotoBtnItemIndex);
        randomlySetGotoBtnIndex();
    }

}