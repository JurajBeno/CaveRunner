import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Automaticky posiela spravy danym objektom:<br />
 * posunDole() - pri stlaceni klavesy DOWN<br />
 * posunHore() - pri stlaceni klavesy UP<br />
 * posunVlavo() - pri stlacen klavesy LEFT<br />
 * posunVpravo() - pri stlaceni klavesy RIGHT<br />
 * aktivuj() - pri stlaceni klavesy ENTER alebo SPACE<br />
 * zrus() - pri stlaceni klavesy ESC<br />
 * tik() - kazdych 0,25 sekundy<br />
 * vyberSuradnice(x, y) - pri kliknuti mysou
 */
public class Manazer {
    private CopyOnWriteArrayList<Object> spravovaneObjekty;
    private ArrayList<Integer> vymazaneObjekty;
    private long oldTick;
    private static final long TICK_LENGTH = 20000000;
    
    private class ManazerKliknutia extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_SPACE) {
                Manazer.this.posliSpravu("utoc");
            } else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                Manazer.this.posliSpravu("aktivuj");
            } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Manazer.this.posliSpravu("ukonci");
            }
        }
    }

    private class ManazerKlaves implements KeyListener {
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                Manazer.this.posliSpravu("chodDole");
            } else if (event.getKeyCode() == KeyEvent.VK_UP) {
                Manazer.this.posliSpravu("chodHore");
            } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                Manazer.this.posliSpravu("chodVlavo");
            } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                Manazer.this.posliSpravu("chodVpravo");
            }
        }

        public void keyTyped(KeyEvent event) {
            Manazer.this.doNothing();
        }

        public void keyReleased(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                Manazer.this.posliSpravu("prestanChoditDole");
            } else if (event.getKeyCode() == KeyEvent.VK_UP) {
                Manazer.this.posliSpravu("prestanChoditHore");
            } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                Manazer.this.posliSpravu("prestanChoditVlavo");
            } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                Manazer.this.posliSpravu("prestanChoditVpravo");
            }
        }
    }
    
    private class ManazerCasovaca implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            long newTick = System.nanoTime();
            if (newTick - Manazer.this.oldTick >= Manazer.TICK_LENGTH || newTick < Manazer.TICK_LENGTH) {
                Manazer.this.oldTick = (newTick / Manazer.TICK_LENGTH) * Manazer.TICK_LENGTH;
                Manazer.this.posliSpravu("tik");
            }
        }
    }
    
    private class ManazerMysi extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                Manazer.this.posliSpravu("vyberSuradnice", event.getX(), event.getY());
            }
        }
    }
    
    private void posliSpravu(String selektor) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                if (adresat != null) {                    
                    Method sprava = adresat.getClass().getMethod(selektor);
                    sprava.invoke(adresat);
                }
            } catch (SecurityException e) {
                this.doNothing();
            } catch (NoSuchMethodException e) {
                this.doNothing();
            } catch (IllegalArgumentException e) {
                this.doNothing();
            } catch (IllegalAccessException e) {
                this.doNothing();
            } catch (InvocationTargetException e) {
                this.doNothing();
            }
        }
        this.removeDeletedObjects();
    }
    
    private void posliSpravu(String selektor, int prvyParameter, int druhyParameter) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                if (adresat != null) {
                    Method sprava = adresat.getClass().getMethod(selektor, Integer.TYPE, Integer.TYPE);
                    sprava.invoke(adresat, prvyParameter, druhyParameter);
                }
            } catch (SecurityException e) {
                this.doNothing();
            } catch (NoSuchMethodException e) {
                this.doNothing();
            } catch (IllegalArgumentException e) {
                this.doNothing();
            } catch (IllegalAccessException e) {
                this.doNothing();
            } catch (InvocationTargetException e) {
                this.doNothing();
            }
        }
        this.removeDeletedObjects();
    }
    
    private void doNothing() {
        
    }
    
    private void removeDeletedObjects() {
        if (this.vymazaneObjekty.size() > 0) {
            Collections.sort(this.vymazaneObjekty, Collections.reverseOrder());
            for (int i = this.vymazaneObjekty.size() - 1; i >= 0; i--) {
                this.spravovaneObjekty.remove(this.vymazaneObjekty.get(i));
            }
            this.vymazaneObjekty.clear();
        }        
    }
    
    /**
     * Vytvori novy manazer, ktory nespravuje zatial ziadne objekty.
     */
    public Manazer() {
        this.spravovaneObjekty = new CopyOnWriteArrayList<Object>();
        this.vymazaneObjekty = new ArrayList<Integer>();
        Platno.dajPlatno().addKeyListener(new ManazerKlaves());
        Platno.dajPlatno().addKeyListener(new ManazerKliknutia());
        Platno.dajPlatno().addTimerListener(new ManazerCasovaca());
        Platno.dajPlatno().addMouseListener(new ManazerMysi());
    }
    
    /**
     * Manazer bude spravovat dany objekt.
     */
    public void spravujObjekt(Object objekt) {
        this.spravovaneObjekty.add(objekt);
    }
    
    /**
     * Manazer prestane spravovat dany objekt.
     */
    public void prestanSpravovatObjekt(Object objekt) {
        int index = this.spravovaneObjekty.indexOf(objekt);
        if (index >= 0) {
            this.spravovaneObjekty.set(index, null);
            this.vymazaneObjekty.add(index);
        }
    }
}
