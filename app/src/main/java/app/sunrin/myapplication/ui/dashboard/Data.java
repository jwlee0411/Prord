package app.sunrin.myapplication.ui.dashboard;

public class Data {


    private String title;
    private String content;

    private String textBeforeChange;
    private String textAfterChange;
    private String textMeaning;
    private String textOtherWord;
    private String textEnglish;

    private int resId;


    public String getTextBeforeChange()
    {
        return textBeforeChange;
    }
    public String getTextAfterChange()
    {
        return textAfterChange;
    }
    public String getTextMeaning()
    {
        return textMeaning;
    }
    public String getTextOtherWord()
    {
        return textOtherWord;
    }
    public String getTextEnglish()
    {
        return textEnglish;
    }

    public void setTextBeforeChange(String textBeforeChange)
    {
        this.textBeforeChange = textBeforeChange;
    }
    public void setTextAfterChange(String textAfterChange)
    {
        this.textAfterChange = textAfterChange;
    }
    public void setTextMeaning(String textMeaning)
    {
        this.textMeaning = textMeaning;
    }
    public void setTextOtherWord(String textOtherWord)
    {
        this.textOtherWord = textOtherWord;
    }
    public void setTextEnglish(String textEnglish)
    {
        this.textEnglish = textEnglish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
