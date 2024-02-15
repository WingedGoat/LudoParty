package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.api.Item;
/** 
 * 
*/
public class BottomPane extends BorderPane {

    private static final int ITEM_BUTTON_WIDHT = 250;
    private static final int ITEM_BUTTON_HEIGTH = 95;
    private Button leftButton = new Button();
    private Button centerButton = new Button();
    private Button rightButton = new Button();
    private final List<Button> buttons = new ArrayList<>();
    private final Map<Button, Item> buttonsMap = new HashMap<>();
    private final Button empybutton = new Button();
    private Map<Button, Boolean> buttonPressed = new HashMap<>();
    private final Alert allert = new Alert(AlertType.NONE);

    /** 
     * The contructor for the BottomPane.
    */
    BottomPane() {
        leftButton.setPrefSize(ITEM_BUTTON_WIDHT, ITEM_BUTTON_HEIGTH);
        leftButton.setDisable(true);
        leftButton.setPrefSize(ITEM_BUTTON_WIDHT, ITEM_BUTTON_HEIGTH);
        centerButton.setDisable(true);
        leftButton.setPrefSize(ITEM_BUTTON_WIDHT, ITEM_BUTTON_HEIGTH);
        rightButton.setDisable(true);
        this.setLeft(leftButton);
        this.setCenter(centerButton);
        this.setRight(rightButton);
        buttons.add(leftButton);
        buttons.add(centerButton);
        buttons.add(rightButton);
        buttonPressed = Map.of(leftButton, false, centerButton, false, rightButton, false);
        buttonsMap.put(leftButton, null);
        buttonsMap.put(centerButton, null);
        buttonsMap.put(rightButton, null);
        this.empybutton.setText("*");
        this.empybutton.setDisable(true);
    }
    /** 
     * @return a copy of the left button
    */
    public final Button getLeftButton() {
        return copyButton(this.leftButton);
    }
    /** 
     * @return a copy of the middle button
    */
    public final Button getCenterButton() {
        return copyButton(this.centerButton);
    }
    /** 
     * @return a copy of the right button
    */
    public final Button getRightButton() {
        return copyButton(this.rightButton);
    }

    /**
     *@return a copy of the empy button
     */
    public final Button getEmpyButton() {
        return copyButton(this.empybutton);
    }
    /**
     * Modify the left button.
     * 
     * @param newButton
     */
    public void setLeftButton(final Button newButton) {
        this.leftButton = newButton;
    }
    /**
     * Modify the center button.
     * 
     * @param newButton
     */
    public void setCenterButton(final Button newButton) {
        this.centerButton = newButton;
    }

    /**
     * Modify the right button.
     * 
     * @param newButton
     */
    public void setRightButton(final Button newButton) {
        this.rightButton = newButton;
    }

    /**
     * Return a copy of the button.
     * 
     * @param button
     * 
     * @return a copy
     */
    public final Button copyButton(final Button button) {
        return button;
    }

    /**
     * Return the buttons list.
     * 
     * @return the buttons list
     */
    public List<Button> getButtons() {
        final List<Button> copyButtons = new ArrayList<>();
        copyButtons.add(getLeftButton());
        copyButtons.add(getCenterButton());
        copyButtons.add(getRightButton());
        return copyButtons;
    }

    /**
     * Return a copy of the button map.
     * 
     * @return a copy
     */
    public final  Map<Button, Item> getButtonMap() {
        final Map<Button, Item> copybuttonsMap = new HashMap<>();
        copybuttonsMap.put(leftButton, buttonsMap.get(leftButton));
        copybuttonsMap.put(centerButton, buttonsMap.get(centerButton));
        copybuttonsMap.put(rightButton, buttonsMap.get(rightButton));
        return copybuttonsMap;
    }

    /**
     * Replace a new entry the button map.
     * 
     * @param button
     *          the button key
     * @param item
     *          the new item to replace
     */
    public void replaceItemButtonsMap(final Button button, final Item item) {
            this.buttonsMap.replace(button, item);
    }

    /**
     * Set the button as pressed.
     * 
     * @param button
     */
    public void buttonPressed(final Button button) {
        buttonPressed.replace(button, true);
    }

    /**
     * To disable all the buttons.
     * 
     * For the inventory at the end of the human player, 
     * for the shop at the end of the turn that a pawn arrive on a shop cell.
     */
    public void disableInventory() {
        for (int i = 0; i < this.getButtons().size(); i++) {
            final Button button = getButtons().get(i);
            button.setDisable(true);
        }
    }

    /**
     * Return the allert used for the message to the player.
     * 
     * @return the allert
     */
    public Alert getAlert() {
        return this.allert;
    }

    /**
     * To able all the buttons of the human player at the start of the turn.
     * 
     * For the inventory at the start of the human player, 
     * for the shop when a pawn arrive on a the cell.
     */
    public void ableInventory() {
        for (int i = 0; i < this.getButtons().size(); i++) {
            final Button button = getButtons().get(i);
            button.setDisable(false);
        }
    }

}
