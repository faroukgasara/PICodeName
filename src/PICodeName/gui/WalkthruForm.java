/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

<<<<<<< HEAD
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;

/**
 * Swiping thru tutorial
 *
 * @author Shai Almog
 */
public class WalkthruForm extends Form {

    public WalkthruForm(Resources res) {
        super(new BorderLayout());
        getTitleArea().setUIID("Container");
        setUIID("Welcome");
        Tabs t = new Tabs();
        t.hideTabs();
        t.setUIID("Container");
        t.getContentPane().setUIID("Container");
        add(BorderLayout.CENTER, t);
        
        ScaleImageLabel page1 = new ScaleImageLabel(res.getImage("welcome-slide-1.png"));
        ScaleImageLabel page2 = new ScaleImageLabel(res.getImage("welcome-slide-2.png"));
        ScaleImageLabel page3 = new ScaleImageLabel(res.getImage("welcome-slide-3.png"));
        page1.setUIID("Container");
        page2.setUIID("Container");
        page3.setUIID("Container");
        page1.getAllStyles().setBgTransparency(0);
        page2.getAllStyles().setBgTransparency(0);
        page3.getAllStyles().setBgTransparency(0);
        t.addTab("", page1);
        t.addTab("", page2);
        t.addTab("", page3);
        
        String[] messages = {
            "Manage your tasks quickly\nand efficiently",
            "This demo is powered by\nCodename One",
            "Start NOW\n press skip"            
        };
        
        SpanLabel message = new SpanLabel(messages[0], "WelcomeMessage");
                

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xcccccc);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xff2d55);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[t.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        t.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
                message.setText(messages[ii]);
            }
        });
        
        Button skip = new Button("Skip");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new SignInForm(res).show());
        
        Container welcomeNoteArea = BoxLayout.encloseY(message,
                LayeredLayout.encloseIn(
                        radioContainer,
                        BorderLayout.east(skip)
                )
        );
        welcomeNoteArea.setUIID("WelcomeNoteArea");
        add(BorderLayout.SOUTH, welcomeNoteArea);
    }
    
}
=======
/**
 *
 * @author farou
 */
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

public class WalkthruForm extends com.codename1.ui.Form {

    public WalkthruForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    class BgPainter implements Painter {
        private Container parent;
        private Image pic;
        private int textHeight;
        
        public BgPainter(Container parent, Image pic, int textHeight) {
            this.parent = parent;
            this.pic = pic;
            this.textHeight = textHeight;
        }

        
        @Override
        public void paint(Graphics g, Rectangle rect) {
            int mm1 = Display.getInstance().convertToPixels(2);
                
            GeneralPath gp = new GeneralPath();
            float x = parent.getX() + mm1;
            float radius = mm1;
            float y = parent.getY() + mm1;
            float widthF = parent.getWidth() - (2 * mm1);
            float heightF = parent.getHeight()- (2 * mm1);
            gp.moveTo(x + radius, y);
            gp.lineTo(x + widthF - radius, y);
            gp.quadTo(x + widthF, y, x + widthF, y + radius);
            gp.lineTo(x + widthF, y + heightF - radius);
            gp.quadTo(x + widthF, y + heightF, x + widthF - radius, y + heightF);
            gp.lineTo(x + radius, y + heightF);
            gp.quadTo(x, y + heightF, x, y + heightF - radius);
            gp.lineTo(x, y + radius);
            gp.quadTo(x, y, x + radius, y);
            gp.closePath();            

            g.setColor(0xffffff);
            g.setAntiAliased(true);
            int [] clip = g.getClip();
            if(g.isShapeClipSupported()) {
                g.setClip(gp);
            } else {
                // we won't have a round rect but at least we will respect its bounds
                Rectangle r = gp.getBounds();
                g.setClip(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            }
            int pw = pic.getWidth();
            float ratio = ((float)pw) / ((float)pic.getHeight());
            int width = parent.getWidth();
            float height = ((float)width) * ratio;
            int hh = (mm1 * 2) + textHeight;
            if(height < parent.getHeight() - hh) {
                hh = (int)(parent.getHeight() - height);
            }
            
            g.drawImage(pic, parent.getX(), parent.getY(), width, (int)height);
            g.setColor(0xffffff);
            g.setAlpha(255);
            g.fillRect(parent.getX(), parent.getY() + parent.getHeight() - hh, parent.getWidth(), hh);
            g.fillTriangle(parent.getX(), parent.getY() + parent.getHeight() - hh, 
                    parent.getX() + parent.getWidth(), parent.getY() + parent.getHeight() - hh,
                    parent.getX() + parent.getWidth(), parent.getY() + parent.getHeight() - hh - (mm1 * 2));
            g.setClip(clip);
        }
        
    }
    
    public WalkthruForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        
        ButtonGroup bg = new ButtonGroup();
        gui_tab1.setToggle(true);
        gui_tab1.setUIID("Label");
        bg.add(gui_tab1);
        gui_tab2.setToggle(true);
        gui_tab2.setUIID("Label");
        bg.add(gui_tab2);
        gui_tab3.setToggle(true);
        gui_tab3.setUIID("Label");
        bg.add(gui_tab3);
        gui_tab1.setSelected(true);
        
        Image bla = resourceObjectInstance.getImage("rdv.jpg");
        Image griffith = resourceObjectInstance.getImage("event.jpg");
        Image learn = resourceObjectInstance.getImage("offre.jpg");
        
        gui_tab1Root.setLayout(new BorderLayout());
        gui_tab2Root.setLayout(new BorderLayout());
        gui_tab3Root.setLayout(new BorderLayout());
        
        String line1 =  "A company can set up an appointment with an employee and send the meet by email to spend it online";
        if(!Display.getInstance().isTablet()) {
            line1 = line1.replace('\n', ' ');
        }

        String line2 =  "The concept of corporate event encompasses company dinners, meetings, conferences, symposiums and even recreational activities";
        if(!Display.getInstance().isTablet()) {
            line2 = line2.replace('\n', ' ');
        }

        String line3 =  "Active conduct with a view to obtaining or regaining employment./Training involves teaching an employee the necessary knowledge and skills. Vocational training is generally adopted for people already exercising a professional activity, and wishing to increase their skills.";
        if(!Display.getInstance().isTablet()) {
            line3 = line3.replace('\n', ' ');
        }
        
        Container content1 = BoxLayout.encloseY(
                new Label("Meeting", "WelcomeTitle"),
                new Label(resourceObjectInstance.getImage("welcome-separator.png"), "WelcomeTitle"),
                new SpanLabel(line1, "WelcomeBody")
        );

        Container content2 = BoxLayout.encloseY(
                new Label("Event", "WelcomeTitle"),
                new Label(resourceObjectInstance.getImage("welcome-separator.png"), "WelcomeTitle"),
                new SpanLabel(line2, "WelcomeBody")
        );

        Container content3 = BoxLayout.encloseY(
                new Label("Offer & Training ", "WelcomeTitle"),
                new Label(resourceObjectInstance.getImage("welcome-separator.png"), "WelcomeTitle"),
                new SpanLabel(line3, "WelcomeBody")
        );

        content1.setUIID("WelcomeContent");
        content2.setUIID("WelcomeContent");
        content3.setUIID("WelcomeContent");

        gui_tab1Root.add(BorderLayout.SOUTH, content1);
        gui_tab2Root.add(BorderLayout.SOUTH, content2);
        gui_tab3Root.add(BorderLayout.SOUTH, content3);

        gui_tab1Root.getUnselectedStyle().setBgPainter(new BgPainter(gui_tab1Root, bla, content1.getPreferredH() +
                content1.getUnselectedStyle().getPaddingTop() + 
                content1.getUnselectedStyle().getPaddingBottom() + 
                content1.getUnselectedStyle().getMarginTop() + 
                content1.getUnselectedStyle().getMarginBottom()));

        gui_tab2Root.getUnselectedStyle().setBgPainter(new BgPainter(gui_tab2Root, griffith, content2.getPreferredH() +
                content2.getUnselectedStyle().getPaddingTop() + 
                content2.getUnselectedStyle().getPaddingBottom() + 
                content2.getUnselectedStyle().getMarginTop() + 
                content2.getUnselectedStyle().getMarginBottom()));

        gui_tab3Root.getUnselectedStyle().setBgPainter(new BgPainter(gui_tab3Root, learn, content3.getPreferredH() +
                content3.getUnselectedStyle().getPaddingTop() + 
                content3.getUnselectedStyle().getPaddingBottom() + 
                content3.getUnselectedStyle().getMarginTop() + 
                content3.getUnselectedStyle().getMarginBottom()));

        
        gui_Tabs_1.addSelectionListener((i, ii) -> {
            switch(ii) {
                case 0:
                    gui_tab1.setSelected(true);
                    break;
                case 1:
                    gui_tab2.setSelected(true);
                    break;
                default:
                    gui_tab3.setSelected(true);
                    break;
            }
        });

        gui_Tabs_1.hideTabs();
        gui_Tabs_1.getContentPane().setUIID("Container");
        //gui_slide1Image
    }

    @Override
    protected Component createStatusBar() {
        Component c = super.createStatusBar();
        c.getUnselectedStyle().setPadding(0, 0, 0, 0);
        return c;
    }

    
    
//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Tabs gui_Tabs_1 = new com.codename1.ui.Tabs();
    private com.codename1.ui.Container gui_tab1Root = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_tab2Root = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Container gui_tab3Root = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.RadioButton gui_tab1 = new com.codename1.ui.RadioButton();
    private com.codename1.ui.RadioButton gui_tab2 = new com.codename1.ui.RadioButton();
    private com.codename1.ui.RadioButton gui_tab3 = new com.codename1.ui.RadioButton();
    private com.codename1.ui.Container gui_Container_6 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_tab3.addActionListener(callback);
        gui_Button_1.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_tab3) {
                ontab3ActionEvent(ev);
            }
            if(sourceComponent == gui_Button_1) {
                onButton_1ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setUIID("Welcome");
        setTitle("");
        setName("WalkthruForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Tabs_1);
        gui_Tabs_1.setName("Tabs_1");
        gui_Tabs_1.addTab("Tab" ,gui_tab1Root);
        gui_Tabs_1.addTab("Tab" ,gui_tab2Root);
        gui_Tabs_1.addTab("Tab" ,gui_tab3Root);
        gui_tab1Root.setUIID("Card");
        gui_tab1Root.setName("tab1Root");
        gui_tab2Root.setName("tab2Root");
        gui_tab3Root.setName("tab3Root");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_4);
        gui_Container_4.setName("Container_4");
        gui_Container_4.addComponent(gui_Container_3);
        gui_Container_3.setName("Container_3");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_3.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_3.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Container_3.addComponent(gui_tab1);
        gui_Container_3.addComponent(gui_tab2);
        gui_Container_3.addComponent(gui_tab3);
        gui_tab1.setSelected(false);
        gui_tab1.setUIID("Label");
        gui_tab1.setName("tab1");
        gui_tab1.setIcon(resourceObjectInstance.getImage("walthru-radio-unselected.png"));
        gui_tab1.setPressedIcon(resourceObjectInstance.getImage("walthru-radio-selected.png"));
        gui_tab2.setUIID("Label");
        gui_tab2.setName("tab2");
        gui_tab2.setIcon(resourceObjectInstance.getImage("walthru-radio-unselected.png"));
        gui_tab2.setPressedIcon(resourceObjectInstance.getImage("walthru-radio-selected.png"));
        gui_tab3.setUIID("Label");
        gui_tab3.setName("tab3");
        gui_tab3.setIcon(resourceObjectInstance.getImage("walthru-radio-unselected.png"));
        gui_tab3.setPressedIcon(resourceObjectInstance.getImage("walthru-radio-selected.png"));
        gui_Container_4.addComponent(gui_Container_6);
        gui_Container_6.setUIID("GetStartedButton");
        gui_Container_6.setName("Container_6");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Container_6.addComponent(gui_Button_1);
        gui_Container_6.addComponent(gui_Label_1);
        gui_Button_1.setText("Get Started");
        gui_Button_1.setUIID("GetStartedButton");
        gui_Button_1.setName("Button_1");
        gui_Button_1.setTextPosition(com.codename1.ui.Component.LEFT);
        gui_Label_1.setUIID("GetStartedRedArrow");
        gui_Label_1.setName("Label_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Label_1,"".charAt(0));
        gui_Container_3.setName("Container_3");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_3.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_3.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Container_6.setUIID("GetStartedButton");
        gui_Container_6.setName("Container_6");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_6.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Tabs_1.setName("Tabs_1");
        gui_Container_4.setName("Container_4");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void ontab3ActionEvent(com.codename1.ui.events.ActionEvent ev) {
    }

    public void onButton_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new Home().show();
    }

}
>>>>>>> c12e508fbf33c72f79e5c2f9bad831dcce6c6ad8
