package net.ingramintegrations.customdialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button standardDialog, buttonDialog, listDialog, customDialog, multipleChoiceDialog;
    EditText username, password;
    AlertDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        standardDialog = (Button) findViewById(R.id.act_main_bt_standard_dialog);
        buttonDialog = (Button) findViewById(R.id.act_main_bt_button_dialog);
        listDialog = (Button) findViewById(R.id.act_main_bt_list_dialog);
        customDialog = (Button) findViewById(R.id.act_main_bt_custom_dialog);
        multipleChoiceDialog = (Button) findViewById(R.id.act_main_bt_multiple_choice_dialog);

        standardDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStandardDialog();
            }
        });

        buttonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithButtons();
            }
        });

        listDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });

        customDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        multipleChoiceDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultipleChoiceDialog();
            }
        });
    }

    private void showListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Select Sport")
                .setItems(R.array.dialog_choices, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int selection) {
                        switch (selection) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(), SportActivity.class));
                                break;
                            case 1:
                                Toast.makeText(
                                        getApplicationContext(),
                                        "You chose Football",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(
                                        getApplicationContext(),
                                        "You chose Soccer",
                                        Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showStandardDialog() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("This is my dialog message.").setTitle("Dialog Title");
        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        // 4. Show the dialog
        dialog.show();
    }

    private void showDialogWithButtons(){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("This is my dialog message.").setTitle("Dialog Title");
        // 3. Add the buttons
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the OK button
                Toast.makeText(getApplicationContext(), "You clicked the ok button.",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                Toast.makeText(getApplicationContext(), "You clicked the cancel button.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the Neutral button
                Toast.makeText(getApplicationContext(), "You clicked the neutral button.", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. Create the AlertDialog
        AlertDialog dialog = builder.create();
        // 5. Show the dialog.
        dialog.show();

    }

    private void showCustomDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                // Add action buttons
                .setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        username = (EditText) dialog.findViewById(R.id.dia_userpass_et_username);
                        password = (EditText) dialog.findViewById(R.id.dia_userpass_et_password);
                        Toast.makeText(getApplicationContext(),"Username: " + username.getText().toString() +
                                " Password: " + password.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_username_and_password, null));

        dialog = builder.create();
        dialog.show();
    }

    private void showMultipleChoiceDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Set the dialog title
        builder.setTitle("Select Your Toppings")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(R.array.toppings, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int topping,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    switch (topping) {
                                        case 0:
                                            Toast.makeText(getApplicationContext(), "Selected: Cheese", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 1:
                                            Toast.makeText(getApplicationContext(), "Selected: Pineapple", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 2:
                                            Toast.makeText(getApplicationContext(), "Selected: Pepperoni", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }else
                                {
                                    // Remove items that were unchecked from the order.
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        Toast.makeText(getApplicationContext(), "Items added to order", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();

    }
}

/* ================== Dialog Button Notes ==================

Source: https://developer.android.com/guide/topics/ui/dialogs.html#CustomLayout

Positive
You should use this to accept and continue with the action (the "OK" action).
Negative
You should use this to cancel the action.
Neutral
You should use this when the user may not want to proceed with the action, but doesn't necessarily want to cancel. It appears between the positive and negative buttons. For example, the action might be "Remind me later."

You can add only one of each button type to an AlertDialog. That is, you cannot have more than one "positive" button.
 */
