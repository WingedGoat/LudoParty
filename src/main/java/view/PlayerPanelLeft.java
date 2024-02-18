package view;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import model.Position;
import model.api.Game;

/**
 * Player panel on the left.
 */
@SuppressWarnings("all")
public final class PlayerPanelLeft extends PlayerPanel {

    private static final double GLOW_LEVEL = 0.8;
    private final PlayerGroup bottomPlayer;
    private PlayerGroup topPlayer;

    /**
     * Constructor.
     * 
     * @param game the game
     */
    public PlayerPanelLeft(final Game game) {
        super(game);
        this.bottomPlayer = createBottomPlayer(this.getBottomPos(), game);
        this.getChildren().add(this.bottomPlayer);

        if (game.getPlayers().size() > 2) {
            this.topPlayer = createTopPlayer(this.getTopPos(), game);
            this.getChildren().add(this.topPlayer);
        }
    }

    @Override
    protected PlayerGroup createBottomPlayer(final Position pos, final Game game) {

        final PlayerGroup g = new PlayerGroup(
                pos,
                game.getHumanPlayer().getName(),
                LABEL_NAME_BOTTOM_Y_LAYOUT,
                LABEL_COINS_BOTTOM_Y_LAYOUT,
                DICE_BOTTOM_Y_LAYOUT
        );

        g.getPlayerAvatarInner().setFill(Color.LIGHTBLUE);

        final ImageView diceImage = g.getDiceImage();
        diceImage.setOnMouseEntered(mouseEvent -> {
            diceImage.setEffect(new Glow(GLOW_LEVEL));
            diceImage.setCursor(Cursor.HAND);
        });
        diceImage.setOnMouseExited(mouseEvent -> {
            diceImage.setEffect(null);
        });
        //TODO add another dice image when is used DADUPLO
        diceImage.setOnMouseClicked(mouseEvent -> {
            if (game.getHumanPlayer().canRollDice()) {
                final int diceResult = game.getHumanPlayer().rollDice();
                game.getTurn().setDiceResult(diceResult);
                //showDiceNumber(diceImage, diceResult);
                /*
                 * Se con il risultato ottenuto non è possibile muovere pedine,
                 * imposto pawnMoved a true, così è già possibile premere ENTER e passare il
                 * turno (senza cliccare Pawn).
                 */
                if (!game.getHumanPlayer().canMovePawns(diceResult)) {
                    game.getHumanPlayer().setPawnMoved(true);
                }
            }
        });

        g.getChildren().addAll(
            g.getPlayerAvatar(), g.getPlayerAvatarInner(), g.getPlayerName(), g.getPlayerCoins(), diceImage);

        return g;
    }

    @Override
    protected PlayerGroup createTopPlayer(final Position pos, final Game game) {

        final PlayerGroup g = new PlayerGroup(
                pos,
                game.getPlayers().get(1).getName(),
                LABEL_NAME_TOP_Y_LAYOUT,
                LABEL_COINS_TOP_Y_LAYOUT,
                DICE_TOP_Y_LAYOUT
        );

        //TODO Change dice image when it is computer player turn
        //TODO add another dice image when is used DADUPLO

        g.getChildren().addAll(
            g.getPlayerAvatar(), g.getPlayerAvatarInner(), g.getPlayerName(), g.getPlayerCoins(), g.getDiceImage());

        return g;
    }

    @Override
    protected Label getBottomPlayerCoins() {
        return this.bottomPlayer.getPlayerCoins();
    }

    @Override
    protected Label getTopPlayerCoins() {
        return this.topPlayer.getPlayerCoins();
    }

    @Override
    protected ImageView getBottomPlayerDice() {
        return this.bottomPlayer.getDiceImage();
    }

    @Override
    protected ImageView getTopPlayerDice() {
        return this.topPlayer.getDiceImage();
    }

    @Override
    public void refresh(final int coinsBottom, final int coinsTop, final int diceBottomNum, final int diceTopNum) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getBottomPlayerCoins().setText("Ludollari: " + coinsBottom);
                if (getPlayersNumber() > 2) {
                    getTopPlayerCoins().setText("Ludollari: " + coinsTop);
                }
            }
        });
    }

}
