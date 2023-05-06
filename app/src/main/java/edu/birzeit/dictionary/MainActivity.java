package edu.birzeit.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.birzeit.dictionary.model.DAWord;
import edu.birzeit.dictionary.model.Word;
public class MainActivity extends AppCompatActivity {

    private Spinner my_spinner;
    private Button my_button;
    private TextView my_messageTextView;
    private ListView my_listOfWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_spinner = findViewById(R.id.spinner);
        my_messageTextView = findViewById(R.id.txtview);
        my_listOfWords = findViewById(R.id.list_words);

        DAWord dictionary = new DAWord();
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, dictionary.getCategories());
        my_spinner.setAdapter(categoryAdapter);

        my_button = findViewById(R.id.btnsubmit);
        my_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedCategory = my_spinner.getSelectedItem().toString();
                List<Word> words = dictionary.getWordsByCategory(selectedCategory);
                if (words.isEmpty()) {
                    my_listOfWords.setAdapter(null);
                } else {
                    ArrayList<String> word_info = new ArrayList<>();
                    for (Word word : words) {
                        String row_data = word.getTitle() + " - " + word.getMeaning()+ " - " +word.getDefinition();
                        word_info.add(row_data);
                    }
                    ArrayAdapter<String> wordAdapter = new ArrayAdapter<>(MainActivity.this,
                            R.layout.list_item_word, R.id.list_item_word_title_and_meaning, word_info);
                    my_listOfWords.setAdapter(wordAdapter);
                }
            }
        });
    }
}
