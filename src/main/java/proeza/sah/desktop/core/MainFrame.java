package proeza.sah.desktop.core;

import javax.swing.JToggleButton;

import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guiBuilder.api.component.GBFrame;
import proeza.sah.device.Device;
import proeza.sah.device.DeviceNetwork;
import proeza.sah.device.DeviceResourceManager;
import proeza.sah.device.DeviceState;

@Component
public class MainFrame extends GBFrame {

    private static final long     serialVersionUID = 1L;

    @Autowired
    private DeviceNetwork         network;

    @Autowired
    private DeviceResourceManager deviceResourceManager;

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
        ParallelGroup pg = gl1.createParallelGroup();
        SequentialGroup sg = gl1.createSequentialGroup();
        gl1.setHorizontalGroup(pg);
        gl1.setVerticalGroup(sg);
        for (Device device : this.network.getDevices()) {
            JToggleButton button = new JToggleButton();
            button.setIcon(this.deviceResourceManager.getResource(device.getStatus().getType()).asIcon());
            button.setSelected(device.getStatus().getState().equals(DeviceState.ON));
            pg.add(button);
            sg.add(button);
        }
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