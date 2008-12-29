/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Copyright (C) 2008 Yan Cheng Cheok <yccheok@yahoo.com>
 */

package org.yccheok.jstock.chat;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.yccheok.jstock.chat.ChatServiceManager.State;
import org.yccheok.jstock.gui.JStockOptions;
import org.yccheok.jstock.gui.MainFrame;

/**
 *
 * @author yccheok
 */
public class ChatJPanel extends javax.swing.JPanel {

    public static class Message
    {
        public enum Mode
        {
            Mine,
            Other,
            System
        };

        public final String who;
        public final String msg;
        public final Mode mode;

        private Message(String who, String msg, Mode mode)
        {
            this.who = who;
            this.msg = msg;
            this.mode = mode;
        }

        public static Message newInstance(String who, String msg, Mode mode) {
            return new Message(who, msg, mode);
        }
    }
    
    /** Creates new form ChatJPanel */
    public ChatJPanel() {
        initComponents();
        this.updateUIAccordingToOptions();

        // Not sure why. Having the JSplitPane resizeWeight and all JList
        // minimum, maximum, preferred size being specified, the divider position
        // still not correct. Unless we have some item inside the JList. Weird ?!
        this.addUser("");
        this.addChannel("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new JTextFieldEx();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Market Chit Chat"));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(5, 5));

        jSplitPane2.setResizeWeight(1.0);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.setText(Utils.getJEditorPaneEmptyHeader());
        jEditorPane1.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEditorPane1HyperlinkUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jEditorPane1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel1.setIcon(connectingIcon);
        jLabel1.setText("yccheok");
        jLabel1.setToolTipText("Not connected");
        jLabel1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/smile-gray.png"))); // NOI18N
        jPanel2.add(jLabel1, java.awt.BorderLayout.WEST);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jSplitPane2.setLeftComponent(jPanel1);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(160, 23));

        jList2.setBorder(javax.swing.BorderFactory.createTitledBorder("Users"));
        jList2.setModel(new DefaultListModel());
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jList2);

        jSplitPane2.setRightComponent(jScrollPane3);

        jSplitPane1.setRightComponent(jSplitPane2);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(160, 23));

        jList1.setBorder(javax.swing.BorderFactory.createTitledBorder("Channels"));
        jList1.setModel(new DefaultListModel());
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1);

        jSplitPane1.setLeftComponent(jScrollPane2);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jEditorPane1HyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_jEditorPane1HyperlinkUpdate
        if (HyperlinkEvent.EventType.ACTIVATED.equals(evt.getEventType())) {
            URL url = evt.getURL();

            if(Desktop.isDesktopSupported())
            {
                Desktop desktop = Desktop.getDesktop();

                if(desktop.isSupported(Desktop.Action.BROWSE))
                {
                    try {
                        desktop.browse(url.toURI());
                    }
                    catch (URISyntaxException ex) {
                    }
                    catch (IOException ex) {
                    }
                }
            }
        }
    }//GEN-LAST:event_jEditorPane1HyperlinkUpdate

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        if (firstTime) {
            this.firstTime = !this.firstTime;
            this.removeChannel("");
            this.removeUser("");
        }
    }//GEN-LAST:event_formComponentShown

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        String text = this.jTextField1.getText();
        if (text.length() <= 0) {
            return;
        }

        this.jTextField1.setText("");
        this.chatServiceManager.sendMessage(text);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void showMessage(final Message message)
    {
        if (SwingUtilities.isEventDispatchThread()) {
            _showMessage(message);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    _showMessage(message);
                }

            });
        }
    }

    private void _showMessage(Message message)
    {
        if (message == null)
        {
            return;
        }

        this.getMainFrame().flashChatTabIfNeeded();
        
        final Component compFocusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();

        final JScrollBar scrollBar = this.jScrollPane1.getVerticalScrollBar();
        // I really have no idea why we need 3 magic number. htmlDocument.getLength()
        // just won't get to the end of text.
        final boolean shouldScroll = ((scrollBar.getVisibleAmount() + scrollBar.getValue() + 3) >= scrollBar.getMaximum());

        final String who = message.who;
        final String msg = message.msg;
        final Message.Mode mode = message.mode;

        HTMLDocument htmlDocument = (HTMLDocument)this.jEditorPane1.getDocument();

        if (htmlDocument.getLength() > (Integer.MAX_VALUE >> 1))
        {
            this.jEditorPane1.setText(Utils.getJEditorPaneEmptyHeader());
            htmlDocument = (HTMLDocument)this.jEditorPane1.getDocument();
        }

        javax.swing.text.Element html = htmlDocument.getRootElements()[0];
        javax.swing.text.Element body = null;
        final int count = html.getElementCount();

        for (int i=0; i<count; i++) {
            javax.swing.text.Element tmp = html.getElement(i);
            if (tmp.getName().equalsIgnoreCase("body"))
            {
                body = tmp;
                break;
            }
        }

        if (body == null) return;

        try {
            htmlDocument.insertBeforeEnd(body, Utils.getHTMLAccordingToMessageMode(who, msg, mode));
        } catch (BadLocationException ex) {
            log.error(null, ex);
        } catch (IOException ex) {
            log.error(null, ex);
        }
        
        if (shouldScroll)
        {
            // Is a must to make setCaretPosition take effect.
            jEditorPane1.requestFocus();
            jEditorPane1.setCaretPosition(htmlDocument.getLength());

            if (compFocusOwner != null)
                compFocusOwner.requestFocus();
        }
    }

    public void startChatServiceManager()
    {
        chatServiceManager.start();
        chatServiceManager.attachPacketObserver(this.chatServiceManagerPacketObserver);
        chatServiceManager.attachStateObserver(this.chatServiceManagerStateObserver);
    }

    public void stopChatServiceManager()
    {
        chatServiceManager.stop();
        chatServiceManager.dettachAllPacketObserver();
        chatServiceManager.dettachAllStateObserver();

        if (timer != null) {
            timer.stop();
            timer = null;
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                jLabel1.setToolTipText("Not connected");
                jLabel1.setIcon(connectingIcon);
            }

        });
    }

    private void _updateUIAccordingToOptions() {
        final JStockOptions jStockOptions = MainFrame.getJStockOptions();
        
        if (jStockOptions.isChatEnabled())
        {
            jLabel1.setEnabled(true);
            jLabel1.setText(MainFrame.getJStockOptions().getChatUsername());
        }
        else
        {
            jLabel1.setEnabled(false);
            jLabel1.setText(MainFrame.getJStockOptions().getChatUsername());
            jLabel1.setToolTipText("Not connected");
        }
    }

    public void updateUIAccordingToOptions() {
        if (SwingUtilities.isEventDispatchThread()) {
            _updateUIAccordingToOptions();
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    _updateUIAccordingToOptions();
                }

            });
        }
    }

    private ActionListener getTimerActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jLabel1.getIcon() == connectedIcon)
                {
                    jLabel1.setIcon(connectingIcon);
                }
                else
                {
                    jLabel1.setIcon(connectedIcon);
                }
            }
        };
    }

    private void update(ChatServiceManager subject, Packet packet) {
        if (packet instanceof Presence)
        {
            // getFrom : malaysia_jstock@conference.jabber.org/yccheok
            // getType() : available
            final Presence presence = (Presence)packet;
            final String[] whos = presence.getFrom().split("/");
            final String who = whos.length >= 2 ? whos[1] : whos[0];

            Message message = null;

            if (presence.getType() == Presence.Type.available) {
                message = Message.newInstance(who, who + " entered the room.", Message.Mode.System);
                this.addUser(who);
                if (MainFrame.getJStockOptions().isChatSoundNotificationEnabled()) {
                    Utils.playSound(Utils.Sound.LOGIN);
                }
            }
            else {
                message = Message.newInstance(who, who + " left the room.", Message.Mode.System);
                this.removeUser(who);
                if (MainFrame.getJStockOptions().isChatSoundNotificationEnabled()) {
                    Utils.playSound(Utils.Sound.LOGOUT);
                }
            }
            
            this.showMessage(message);            
        }
        else if (packet instanceof org.jivesoftware.smack.packet.Message)
        {
            final org.jivesoftware.smack.packet.Message message = (org.jivesoftware.smack.packet.Message)packet;
            final String[] whos = message.getFrom().split("/");
            final String who = whos.length >= 2 ? whos[1] : whos[0];

            final Message msg = Message.newInstance(who, message.getBody(), MainFrame.getJStockOptions().getChatUsername().equals(who) ? Message.Mode.Mine : Message.Mode.Other);

            if (msg.mode == Message.Mode.Mine)
            {
                if (MainFrame.getJStockOptions().isChatSoundNotificationEnabled()) {
                    Utils.playSound(Utils.Sound.SEND);
                }
            }
            else
            {
                if (MainFrame.getJStockOptions().isChatSoundNotificationEnabled()) {
                    Utils.playSound(Utils.Sound.RECEIVE);
                }
            }

            this.showMessage(msg);
        }
        else
        {
        }
    }

    private void update(ChatServiceManager subject, State state) {
        switch(state)
        {
        case CONNECTING:
            if (timer == null)
            {
                timer = new Timer(TIMER_DELAY, getTimerActionListener());
                timer.setInitialDelay(0);
                timer.start();
            }


            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jLabel1.setToolTipText("Connecting to chat server...");
                    if (!firstTime) {
                        // Not sure why. Having the JSplitPane resizeWeight and all JList
                        // minimum, maximum, preferred size being specified, the divider position
                        // still not correct. Unless we have some item inside the JList. Weird ?!
                        ChatJPanel.this.removeAllUsers();
                        ChatJPanel.this.removeAllChannels();
                    }
                }
            });

            break;

        case CONNECTED:
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jLabel1.setToolTipText("Connected");
                    jLabel1.setText(MainFrame.getJStockOptions().getChatUsername());                    
                }
            });
            break;

        case ACCOUNT_CREATING:
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jLabel1.setToolTipText("Creating new account...");
                }
            });
            break;

        case ROOM_CREATING:
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jLabel1.setToolTipText("Creating new room...");                    
                }
            });
            break;

        case ROOM_CREATED:
            if (timer != null)
            {
                timer.stop();
                timer = null;
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jLabel1.setToolTipText("Room created");
                    jLabel1.setIcon(connectedIcon);
                    ChatJPanel.this.addChannel(Utils.getRoomName(MainFrame.getJStockOptions().getCountry()));
                }
            });
            break;
        }
    }

    private MainFrame getMainFrame()
    {
        if (mainFrame == null)
        {
            mainFrame = MainFrame.getMe();
        }

        return mainFrame;
    }

    private org.yccheok.jstock.engine.Observer<ChatServiceManager, Packet> getChatServiceManagerPacketObserver()
    {
        return new org.yccheok.jstock.engine.Observer<ChatServiceManager, Packet>() {
            @Override
            public void update(ChatServiceManager subject, Packet arg) {
                ChatJPanel.this.update(subject, arg);
            }
        };
    }

    private org.yccheok.jstock.engine.Observer<ChatServiceManager, ChatServiceManager.State> getChatServiceManagerStateObserver()
    {
        return new org.yccheok.jstock.engine.Observer<ChatServiceManager, ChatServiceManager.State>() {

            @Override
            public void update(ChatServiceManager subject, State arg) {
                ChatJPanel.this.update(subject, arg);
            }

        };
    }

    public void clearListsSelection()
    {
        if (SwingUtilities.isEventDispatchThread()) {
            jList1.getSelectionModel().clearSelection();
            jList2.getSelectionModel().clearSelection();
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    jList1.getSelectionModel().clearSelection();
                    jList2.getSelectionModel().clearSelection();
                }

            });
        }
    }

    private void addUser(final String name)
    {
        if (SwingUtilities.isEventDispatchThread()) {
            ((DefaultListModel)(jList2.getModel())).addElement(name);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    if (((DefaultListModel)(jList2.getModel())).contains(name) == false) {
                        ((DefaultListModel)(jList2.getModel())).addElement(name);
                    }
                }

            });
        }
    }

    private void removeUser(final String name) {
        if (SwingUtilities.isEventDispatchThread()) {
            ((DefaultListModel)(jList2.getModel())).removeElement(name);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ((DefaultListModel)(jList2.getModel())).removeElement(name);
                }

            });
        }
    }

    private void removeAllUsers() {
        if (SwingUtilities.isEventDispatchThread()) {
            ((DefaultListModel)(jList2.getModel())).removeAllElements();
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ((DefaultListModel)(jList2.getModel())).removeAllElements();
                }

            });
        }
    }

    private void addChannel(final String channel)
    {
        if (SwingUtilities.isEventDispatchThread()) {
            ((DefaultListModel)(jList1.getModel())).addElement(channel);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ((DefaultListModel)(jList1.getModel())).addElement(channel);
                }

            });
        }
    }

    private static class JTextFieldEx extends JTextField implements KeyListener {
        private static final int MEMORY_SIZE = 100;
        private static final List<String> memories = new ArrayList<String>();
        private int read_index  =0;
        private int write_index  =0;

        public JTextFieldEx() {
            super();
            this.addKeyListener(this);
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP)
            {
                read_index--;                
                if (read_index < 0) {
                    read_index = (memories.size() - 1);
                }
                this.setText(memories.get(read_index));
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                read_index++;
                read_index = read_index % memories.size();
                this.setText(memories.get(read_index));
            }
            else if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                memories.add(write_index, this.getText());
                read_index = write_index;
                write_index++;
                write_index = write_index % MEMORY_SIZE;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private void removeChannel(final String channel) {
        if (SwingUtilities.isEventDispatchThread()) {
            ((DefaultListModel)(jList1.getModel())).removeElement(channel);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ((DefaultListModel)(jList1.getModel())).removeElement(channel);
                }

            });
        }
    }

    private void removeAllChannels() {
        if (SwingUtilities.isEventDispatchThread()) {
            ((DefaultListModel)(jList1.getModel())).removeAllElements();
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    ((DefaultListModel)(jList1.getModel())).removeAllElements();
                }

            });
        }
    }

    private final ChatServiceManager chatServiceManager = new ChatServiceManager();
    private final org.yccheok.jstock.engine.Observer<ChatServiceManager, Packet> chatServiceManagerPacketObserver = this.getChatServiceManagerPacketObserver();
    private final org.yccheok.jstock.engine.Observer<ChatServiceManager, ChatServiceManager.State> chatServiceManagerStateObserver = this.getChatServiceManagerStateObserver();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private final javax.swing.ImageIcon connectedIcon = new javax.swing.ImageIcon(getClass().getResource("/images/16x16/smile.png"));
    private final javax.swing.ImageIcon connectingIcon = new javax.swing.ImageIcon(getClass().getResource("/images/16x16/smile-gray.png"));

    private static final Log log = LogFactory.getLog(ChatJPanel.class);

    private boolean firstTime = true;

    private MainFrame mainFrame = null;

    private Timer timer = null;
    private static final int TIMER_DELAY = 500;
}