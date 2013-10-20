/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.client.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import main.client.AppManager;

/**
 *
 * @author Митя
 */
public class LoadingWindow extends JDialog {

    private JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Thread currentThread;

    public LoadingWindow(Window owner) {
        super(owner, "Загрузка...", Dialog.ModalityType.DOCUMENT_MODAL);
        this.getContentPane().setLayout(new BorderLayout());
        progressBar.setPreferredSize(new Dimension(200, 15));
        progressBar.setIndeterminate(true);
        this.getContentPane().add(progressBar, BorderLayout.CENTER);
        this.pack();
    }

    public void doJob(Runnable job) {
        Thread t = new Thread(job);
        currentThread = t;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (currentThread != null && currentThread.isAlive()) {
                    setLocationRelativeTo(AppManager.getMainFrame());
                    setVisible(true);
                }
            }
        });
        t.start();
        new Thread(new CloseOperation(t)).start();
    }

    class CloseOperation implements Runnable {

        private Thread t;

        public CloseOperation(Thread t) {
            this.t = t;
        }

        @Override
        public void run() {
            try {
                t.join();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                    }
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadingWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        executor.shutdown();
    }
}
