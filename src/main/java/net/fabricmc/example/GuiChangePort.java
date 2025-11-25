package net.fabricmc.example;
import net.minecraft.src.*;

public class GuiChangePort extends GuiIngameMenu {
    private GuiScreen parentGuiScreen;
    private GuiButton doneBtn;
    private GuiTextField entryField;
    private String text;
    private boolean enabled = true;


    public GuiChangePort(GuiScreen parent,String text){
        this.parentGuiScreen = parent;
        this.text = text;
    }

    public  void setSharedString(String value) {
        text = value;
    }

    @Override
    public void initGui() {
        this.buttonList.add(this.doneBtn = (new GuiButton(0, this.width / 2 - 100, 124, "Done")));
        this.entryField = (new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 300, 20));
        this.entryField.setMaxStringLength(32767);
        this.entryField.setFocused(true);
        this.entryField.setText(text);
        this.enabled = true;
    }
    @Override
    protected void keyTyped(char c, int i) {
        if ((i >= 2 && i <= 11 ) || i == 14 || i == 71 || i == 72 || i == 73 || i == 75 || i == 76 || i == 77 || i ==79 || i == 80 || i == 81 || i == 82) {
            this.entryField.textboxKeyTyped(c, i);
            if (this.doneBtn.enabled) {
                if (i != 28 && i != 156) {
                    if (i == 1) {
                        this.actionPerformed(this.doneBtn);
                    }
                } else {
                    this.actionPerformed(this.doneBtn);
                }
            }
        }
    }
    @Override
    protected void mouseClicked(int i, int j, int k) {

        super.mouseClicked(i, j, k);
        this.entryField.mouseClicked(i, j, k);

    }
    @Override
    public void updateScreen() {

        this.entryField.updateCursorCounter();
    }
    @Override
    public void drawScreen(int i, int j, float f) {
        if (enabled) {
            this.drawDefaultBackground();
            this.drawCenteredString(this.fontRenderer, "Set Port (Max: 65535) (Leave Empty For Random Vanilla Port)", this.width / 2, 20, 16777215);
            this.drawCenteredString(this.fontRenderer, "Usage of ports below 1024 is discouraged as most of them are reserved.", this.width / 2, 40, 16777215);
            for (int var4 = 0; var4 < this.buttonList.size(); ++var4) {
                GuiButton var5 = (GuiButton) this.buttonList.get(var4);
                var5.drawButton(this.mc, i, j);
            }
            this.entryField.drawTextBox();
        }
        else{
            this.onGuiClosed();
        }
    }
    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == 0) {
                this.setSharedString(this.entryField.getText());
                StringBridge.s = this.entryField.getText();
                this.enabled = false;
                this.mc.displayGuiScreen(new GuiShareToLan(new GuiIngameMenu()));
            }
        }
    }

}
