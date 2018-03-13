package com.junctionseven.richardstockdale.macaddressinputfield;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by richardstockdale on 13/03/2018.
 */

public class MacAddressLayout extends RelativeLayout {
    private Context mContext;

    private ImageButton mDeleteButton;
    private EditText mOctet1Field;
    private EditText mOctet2Field;
    private EditText mOctet3Field;
    private EditText mOctet4Field;
    private EditText mOctet5Field;
    private EditText mOctet6Field;

    private LinearLayout mFocusSump;

    private EditText[] mEditTexts;

    private Boolean textWatcherShouldChangeContent;

    private MacAddressLayoutChangeWatcher mMacAddressChangeWatcher;

    private OnFocusChangeListener mFocusChangeListener;

    private static int sMaxLength = 2;

    //==============================================================================================
    // Public Getters and Setters
    //==============================================================================================

    public String getMacAddress() {
        StringBuilder address = new StringBuilder();
        address.append(mOctet1Field.getText().toString());
        address.append(":");
        address.append(mOctet2Field.getText().toString());
        address.append(":");
        address.append(mOctet3Field.getText().toString());
        address.append(":");
        address.append(mOctet4Field.getText().toString());
        address.append(":");
        address.append(mOctet5Field.getText().toString());
        address.append(":");
        address.append(mOctet6Field.getText().toString());

        return address.toString();
    }

    public void setMacAddress(String macAddress) {
        String stripped = macAddress.replaceAll(":", "");

        // Divide the strings up in to strings with 2 chars each
        ArrayList<StringBuilder> strings = new ArrayList<StringBuilder>();
        for (int i = 0; i < stripped.length(); i++) {
            Character c = stripped.charAt(i);

            if (strings.size() == 0) {
                StringBuilder b = new StringBuilder(c.toString());
                strings.add(b);

                continue;
            }

            StringBuilder last = strings.get(strings.size() - 1);
            if (last.length() == 2) { // Start another
                StringBuilder b = new StringBuilder(c.toString());
                strings.add(b);

                continue;
            }

            last.append(c.toString());
        }

        // Populate the fields
        textWatcherShouldChangeContent = false;
        for (int i = 0; i < strings.size(); i++) {
            StringBuilder sb = strings.get(i);
            switch (i) {
                case 0: {
                    mOctet1Field.setText(sb.toString());
                    break;
                }
                case 1: {
                    mOctet2Field.setText(sb.toString());
                    break;
                }
                case 2: {
                    mOctet3Field.setText(sb.toString());
                    break;
                }
                case 3: {
                    mOctet4Field.setText(sb.toString());
                    break;
                }
                case 4: {
                    mOctet5Field.setText(sb.toString());
                    break;
                }
                case 5: {
                    mOctet6Field.setText(sb.toString());
                    break;
                }
            }

        }
        textWatcherShouldChangeContent = true;
    }

    public void setMacAddressLayoutChangeWatcher(MacAddressLayoutChangeWatcher changeWatcher) {
        mMacAddressChangeWatcher = changeWatcher;
    }

    //==============================================================================================
    // TEXTFIELD SETUP
    //==============================================================================================

    private void setUpTextFieldChangeListeners() {
        mOctet1Field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                processTextChangesForEditText(mOctet1Field);
            }
        });
        mOctet2Field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                processTextChangesForEditText(mOctet2Field);
            }
        });
        mOctet3Field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                processTextChangesForEditText(mOctet3Field);
            }
        });
        mOctet4Field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                processTextChangesForEditText(mOctet4Field);
            }
        });
        mOctet5Field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                processTextChangesForEditText(mOctet5Field);
            }
        });
        mOctet6Field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                processTextChangesForEditText(mOctet6Field);
            }
        });
    }

    private void setUpTextFieldFocusListeners() {
        mFocusChangeListener = new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                // Check if any of the fields has focus
                // If no field has focus, then the user is likely to have finished their edits
                for (EditText field : mEditTexts) {
                    if (field.hasFocus() == true) {
                        return;
                    }
                }

                editingCompleted();
            }
        };

        mOctet1Field.setOnFocusChangeListener(mFocusChangeListener);
        mOctet2Field.setOnFocusChangeListener(mFocusChangeListener);
        mOctet3Field.setOnFocusChangeListener(mFocusChangeListener);
        mOctet4Field.setOnFocusChangeListener(mFocusChangeListener);
        mOctet5Field.setOnFocusChangeListener(mFocusChangeListener);
        mOctet6Field.setOnFocusChangeListener(mFocusChangeListener);
    }

    private void setUpDeleteButton() {
        final MacAddressLayout finalThis = this;
        mDeleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMacAddressChangeWatcher != null) {
                    mMacAddressChangeWatcher.deleteTapped(finalThis);
                }
            }
        });
    }

    private void editingCompleted() {
        final MacAddressLayout finalThis = this;
        if (mMacAddressChangeWatcher != null) {
            mMacAddressChangeWatcher.editingCompleted(finalThis);
        }
    }

    private void processTextChangesForEditText(EditText editText) {
        if (textWatcherShouldChangeContent == false) {
            return;
        }

        String newText = editText.getText().toString().toLowerCase();

        if (newText.length() == 0) { // Then the user has deleted. Skip back to previous field
            EditText lastField = moveToLastFieldFrom(editText);
            if (lastField != null) {
                lastField.setSelection(lastField.getText().length());
            }

            return;
        }

        String sanitizedString = sanitizeString(newText);
        if (newText != sanitizedString) {
            textWatcherShouldChangeContent = false;
            editText.setText(sanitizedString);
            textWatcherShouldChangeContent = true;
            editText.setSelection(editText.getText().toString().length());
        }

        // If this is the last field and its full then remove focus
        if (editText == mOctet6Field && sanitizedString.length() == 2) {
            moveToNextFieldFrom(editText);

            // Hide the keyboard
            InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mFocusSump.getWindowToken(), 0);

            return;
        }

        // Don't let the user enter more than 2 chars.
        if (sanitizedString.length() > sMaxLength) {
            String s = sanitizedString.substring(0, sMaxLength);
            String nextFieldS = sanitizedString.substring(sMaxLength, sanitizedString.length());
            textWatcherShouldChangeContent = false;
            editText.setText(s);
            EditText nextField = moveToNextFieldFrom(editText);
            if (nextField != null) {
                nextField.setText(nextFieldS);
                nextField.setSelection(nextField.getText().length());
                textWatcherShouldChangeContent = true;
            }
        }
    }

    private  EditText moveToLastFieldFrom(EditText editText) {
        Integer currentEditTextIndex = indexOfEditText(editText);
        if (currentEditTextIndex != null) {
            switch (currentEditTextIndex) {
                case 1: {
                    mOctet1Field.requestFocus();
                    return mOctet1Field;
                }
                case 2: {
                    mOctet2Field.requestFocus();
                    return mOctet2Field;
                }
                case 3: {
                    mOctet3Field.requestFocus();
                    return mOctet3Field;
                }
                case 4: {
                    mOctet4Field.requestFocus();
                    return mOctet4Field;
                }
                case 5: {
                    mOctet5Field.requestFocus();
                    return mOctet5Field;
                }
                default: {
                    return null;
                }
            }
        }
        else {
            return null;
        }
    }

    private EditText moveToNextFieldFrom(EditText editText) {
        Integer currentEditTextIndex = indexOfEditText(editText);
        if (currentEditTextIndex != null) {
            switch (currentEditTextIndex) {
                case 0: {
                    mOctet2Field.requestFocus();
                    return mOctet2Field;
                }
                case 1: {
                    mOctet3Field.requestFocus();
                    return mOctet3Field;
                }
                case 2: {
                    mOctet4Field.requestFocus();
                    return mOctet4Field;
                }
                case 3: {
                    mOctet5Field.requestFocus();
                    return mOctet5Field;
                }
                case 4: {
                    mOctet6Field.requestFocus();
                    return mOctet6Field;
                }
                default: {
                    mFocusSump.requestFocus();
                    return null;
                }
            }
        }
        else {
            return null;
        }
    }

    private String sanitizeString(String candidate) {
        String regex = "^*[0-9a-fA-F]*$";

        // Check the text matches the Mac address formatting requirements
        if (candidate.matches(regex)) {
            return candidate;
        }
        else { // Sanitize the string

            // Remove the offending characters
            ArrayList<Character> charactersToRemove = new ArrayList<Character>();

            // Find the offending characters
            for (int i = 0; i < candidate.length(); i++) {
                Character c = candidate.charAt(i);
                String charString = c.toString();

                if (charString.matches(regex) == false) {
                    charactersToRemove.add(c);
                }
            }

            // Remove the offending characters
            String modString = new String(candidate);
            for (Character c : charactersToRemove) {
                modString = modString.replaceAll(c.toString(), "");
            }

            return modString;
        }
    }

    private Integer indexOfEditText(EditText editText) {
        for (int i = 0; i < mEditTexts.length; i++) {
            EditText candidate = mEditTexts[i];
            if (candidate == editText) {
                return i;
            }
        }

        return null;
    }

    //==============================================================================================
    // INITIALISATION
    //==============================================================================================

    public MacAddressLayout(Context context) {
        super(context);
        initializeViews(context);
    }

    public MacAddressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public MacAddressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    public MacAddressLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        mContext = context;


        inflate(context, R.layout.mac_address_layout, this);

        mFocusSump = findViewById(R.id.focus_sump);
        mFocusSump.requestFocus();

        mDeleteButton = findViewById(R.id.delete_button);
        mOctet1Field = findViewById(R.id.octet1);
        mOctet2Field = findViewById(R.id.octet2);
        mOctet3Field = findViewById(R.id.octet3);
        mOctet4Field = findViewById(R.id.octet4);
        mOctet5Field = findViewById(R.id.octet5);
        mOctet6Field = findViewById(R.id.octet6);

        // Roll the fields into an array so we can loop over them
        mEditTexts = new EditText[6];
        mEditTexts[0] = mOctet1Field;
        mEditTexts[1] = mOctet2Field;
        mEditTexts[2] = mOctet3Field;
        mEditTexts[3] = mOctet4Field;
        mEditTexts[4] = mOctet5Field;
        mEditTexts[5] = mOctet6Field;

        textWatcherShouldChangeContent = true;

        setUpTextFieldChangeListeners();
        setUpTextFieldFocusListeners();
        setUpDeleteButton();
    }
}
