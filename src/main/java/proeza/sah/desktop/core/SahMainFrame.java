package proeza.sah.desktop.core;

import org.jdesktop.layout.GroupLayout;

import com.guiBuilder.api.component.GBFrame;

public class SahMainFrame extends GBFrame {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<?> getReferenceClass() {
        return getClass();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void uninit() {

    }

    @Override
    protected void arrange() {
        arrangePanels();
        arrangeMainPanel();
    }

    private void arrangePanels() {
        GroupLayout gl = getLayout();
        gl.setAutocreateGaps(true);
        gl.setAutocreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createParallelGroup().add(this.manager.getPanel(R.PANELS.MAIN)));
        gl.setVerticalGroup(gl.createSequentialGroup().add(this.manager.getPanel(R.PANELS.MAIN)));
    }

    private void arrangeMainPanel() {
        final GroupLayout gl1 = (GroupLayout) this.manager.getPanel(R.PANELS.MAIN).getLayout();
        gl1.setAutocreateContainerGaps(true);
        gl1.setAutocreateGaps(true);
        gl1.setHorizontalGroup(
                gl1.createParallelGroup()
                .add(this.manager.getImageToggleButton(R.IMAGE_TOGGLE_BUTTONS.LED_SWITCH))
                .add(this.manager.getButton(R.BUTTONS.STATUS))
                );
        gl1.setVerticalGroup(
                gl1.createSequentialGroup()
                .add(this.manager.getImageToggleButton(R.IMAGE_TOGGLE_BUTTONS.LED_SWITCH))
                .add(this.manager.getButton(R.BUTTONS.STATUS))
                );
    }

    @Override
    public void update() {

    }

    @Override
    public void doSettings() {

    }

    @Override
    protected String getUIDefFile() {
        return "/sah-main-window.xml";
    }

    @Override
    protected String getUITxtFile() {
        return null;
    }
}