package com.example.to_dolist;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.to_dolist.Adapter.ToDoAdapter;
import com.example.to_dolist.Model.ToDoModel;
import com.example.to_dolist.Utils.DatabaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "AddNewTask";

    private EditText mEditText;
    private Button mSaveButton;

    private DatabaseHelper myDB;

    public static AddNewTask newInstance() {
        return new AddNewTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_new_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.editText);
        mSaveButton = view.findViewById(R.id.addButton);

        myDB = new DatabaseHelper(getActivity());

        final boolean[] isUpdate = {false};  // Ensure 'isUpdate' is accessible in the lambda

        final Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate[0] = true;
            String task = bundle.getString("task");
            mEditText.setText(task);

            if (task.length() > 0) {
                mSaveButton.setEnabled(false);
            }
        }

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    mSaveButton.setEnabled(false);
                    mSaveButton.setBackgroundColor(Color.GRAY); // Fixed color issue
                } else {
                    mSaveButton.setEnabled(true);
                    mSaveButton.setBackgroundColor(Color.BLUE); // Re-enable the button
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Using lambda expression now works because 'isUpdate' is declared effectively final
        mSaveButton.setOnClickListener(view1 -> {
            String text = mEditText.getText().toString();
            if (isUpdate[0]) {  // Access the value inside the array (mutable)
                myDB.updateTask(bundle.getInt("ID"), text); // Corrected method name
            } else {
                ToDoModel item = new ToDoModel();
                item.setTask(text);
                item.setStatus(0);
                myDB.insertTask(item);
            }
            dismiss();
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        // Handle dismissal if needed
        if (activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogClose(dialog);
        }
    }
}
