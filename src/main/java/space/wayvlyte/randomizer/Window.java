package space.wayvlyte.randomizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    private final JFrame Frame = new JFrame();
    private JLabel OutputItem = new JLabel();
    private final DefaultListModel<String> ChanceModel = new DefaultListModel<>();
    private final JList<String> ChancedList = new JList<>(ChanceModel);
    private final JScrollPane ChancedItems = new JScrollPane(ChancedList);

    private JTextField ItemField;

    private void SetupFrame() {
        Frame.setLayout(new GridLayout(2, 1));
        Frame.setName("Randomizer");
        Frame.setSize(600, 400);
    }

    private void AddComponents() {
        JPanel MainPanel = new JPanel();
        JPanel AddAndSubtractPanel = new JPanel();

        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.PAGE_AXIS));
        AddAndSubtractPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton AddButton = new JButton("Add");
        JButton RemoveButton = new JButton("Remove");
        JButton RollButton = new JButton("Roll");

        AddAndSubtractPanel.add(AddButton);
        AddAndSubtractPanel.add(RemoveButton);
        AddAndSubtractPanel.add(RollButton);

        AddButton.addActionListener(this::HandleAddItem);
        RemoveButton.addActionListener(this::HandleRemoveItem);
        RollButton.addActionListener(this::Roll);

        ItemField = new JTextField(16);
        MainPanel.add(ItemField);

        MainPanel.add(ChancedItems);
        MainPanel.add(AddAndSubtractPanel);

        OutputItem.setFont(new Font("Arial", Font.PLAIN, 32));

        MainPanel.add(OutputItem);
        Frame.add(MainPanel);
    }

    private void Roll(ActionEvent L) {
        if (ChanceModel.isEmpty()) return;

        int RandomIndex = (int) (Math.random() * ChanceModel.getSize());
        String RandomItem = ChanceModel.getElementAt(RandomIndex);
        OutputItem.setText(RandomItem);
    }

    private void HandleRemoveItem(ActionEvent L) {
        int SelectedItemIndex = ChancedList.getSelectedIndex();
        if (SelectedItemIndex != -1) {
            ChanceModel.remove(SelectedItemIndex);
        }
    }

    private void HandleAddItem(ActionEvent L) {
        String Item = ItemField.getText();
        if (!Item.isEmpty()) {
            ChanceModel.addElement(Item);
        }
    }

    public void Run() {
        SetupFrame();
        AddComponents();
        Frame.setVisible(true);
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            new Window().Run();
        });
    }
}
