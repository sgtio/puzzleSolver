import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private static Puzzle puzzleInicial;
	private Puzzle puzzleActual;
	private JButton btnResolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		puzzleInicial = new Puzzle ("puzle.txt");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(puzzleInicial);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(final Puzzle p) {
		
		puzzleActual = p;
		
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnJuego = new JMenu("Juego");
		menuBar.add(mnJuego);
		
		JMenuItem mntmCargar = new JMenuItem("Cargar");
		mnJuego.add(mntmCargar);
		
		JMenu mnTipoDePuzzle = new JMenu("Tipo de puzzle");
		mnJuego.add(mnTipoDePuzzle);
		
		JRadioButtonMenuItem rbutton3x3 = new JRadioButtonMenuItem("3x3");
		mnTipoDePuzzle.add(rbutton3x3);
		
		JRadioButtonMenuItem rbutton4x4 = new JRadioButtonMenuItem("4x4");
		mnTipoDePuzzle.add(rbutton4x4);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnAyuda.add(mntmHelp);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de N-Puzzle Solver");
		mnAyuda.add(mntmAcercaDe);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.EAST);
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[]{100};
		gbl_panelBotones.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelBotones.columnWeights = new double[]{0.0};
		gbl_panelBotones.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBotones.setLayout(gbl_panelBotones);
		
		JButton btnMezclar = new JButton("Mezclar Piezas");
		btnMezclar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				puzzleActual.mezclar(puzzleActual);
				/*
				 * Hay que pintar para refrescar, bien aqui o bien en el metodo mezclar
				 * Para pintar hay que usar Canvas (creo) asi que no vale el Panel que esta puesto con los numeros
				 */
			}
		});
		GridBagConstraints gbc_btnMezclar = new GridBagConstraints();
		gbc_btnMezclar.anchor = GridBagConstraints.WEST;
		gbc_btnMezclar.insets = new Insets(5, 5, 5, 5);
		gbc_btnMezclar.gridx = 0;
		gbc_btnMezclar.gridy = 0;
		panelBotones.add(btnMezclar, gbc_btnMezclar);
		
		btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Mostrar animacion unicamente de la solucion del puzzle
				 */
				
				/*
				 * Poner hilo de ejecucion para que resuelva el puzzle
				 * new Thread() {
				 * run() {
				 * ... //con repaint
				 * }
				 * } start();
				 */
//				PuzzleSolver ps = new PuzzleSolver();
//				ps.tableros.add(puzzleActual.representar());
//				Puzzle puzleSolucion = new Puzzle(puzzleActual.solucion());
//
//				int[] posNull = puzzleActual.getPosicion(puzzleActual, "-");
//				if (ps.backtrack(puzzleActual, posNull[0], posNull[1], puzleSolucion))
//					System.out.println("El Puzzle se ha resuelto correctamente");
//				else
//					System.out.println("No se ha encontrado solucion para el puzzle");

			}
		});
		GridBagConstraints gbc_btnResolver = new GridBagConstraints();
		gbc_btnResolver.insets = new Insets(0, 0, 5, 0);
		gbc_btnResolver.gridx = 0;
		gbc_btnResolver.gridy = 1;
		panelBotones.add(btnResolver, gbc_btnResolver);
		
		JButton btnBacktrack = new JButton("Backtracking");
		btnBacktrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * Mostrar animacion del backtracking
				 */
			}
		});
		GridBagConstraints gbc_backtrack = new GridBagConstraints();
		gbc_backtrack.insets = new Insets(0, 0, 5, 0);
		gbc_backtrack.gridx = 0;
		gbc_backtrack.gridy = 2;
		panelBotones.add(btnBacktrack, gbc_backtrack);
		
		JButton initState = new JButton("Estado Inicial");
		initState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				puzzleActual = puzzleInicial;
				//repaint
			}
		});
		GridBagConstraints gbc_stop = new GridBagConstraints();
		gbc_stop.insets = new Insets(0, 0, 5, 0);
		gbc_stop.gridx = 0;
		gbc_stop.gridy = 3;
		panelBotones.add(initState, gbc_stop);
		
		JButton btnStop = new JButton("Detener");
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_panelTablero = new GridBagConstraints();
		gbc_panelTablero.insets = new Insets(0, 0, 5, 0);
		gbc_panelTablero.gridx = 0;
		gbc_panelTablero.gridy = 4;
		panelBotones.add(btnStop, gbc_panelTablero);
		
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		JPanel panel_5 = new JPanel();
		JPanel panel_6 = new JPanel();
		JPanel panel_7 = new JPanel();
		JPanel panel_8 = new JPanel();
		JPanel panel_9 = new JPanel();
		JPanel panel_10 = new JPanel();
		
		
		JPanel panel = new JPanel();
			 	panel.add(panel_2);
				panel.add(panel_3);
				panel.add(panel_4);
				panel.add(panel_5);
				panel.add(panel_6);
				panel.add(panel_7);
				panel.add(panel_8);
				panel.add(panel_9);
				panel.add(panel_10);	
				contentPane.add(panel, BorderLayout.CENTER);
				panel.setLayout(new GridLayout(3, 3, 0, 0));
		
//		JPanel panel_2 = new JPanel();
//		panel.add(panel_2);
//		
//		JPanel panel_3 = new JPanel();
//		panel.add(panel_3);
//		
//		JPanel panel_4 = new JPanel();
//		panel.add(panel_4);
//		
//		JPanel panel_5 = new JPanel();
//		panel.add(panel_5);
//		
//		JPanel panel_6 = new JPanel();
//		panel.add(panel_6);
//		
//		JPanel panel_7 = new JPanel();
//		panel.add(panel_7);
//		
//		JPanel panel_8 = new JPanel();
//		panel.add(panel_8);
//		
//		JPanel panel_9 = new JPanel();
//		panel.add(panel_9);
//		
//		JPanel panel_10 = new JPanel();
//		panel.add(panel_10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		
		JLabel lblNpuzzleSolver = new JLabel("N-Puzzle Solver");
		lblNpuzzleSolver.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNpuzzleSolver.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNpuzzleSolver = new GridBagConstraints();
		gbc_lblNpuzzleSolver.gridx = 0;
		gbc_lblNpuzzleSolver.gridy = 0;
		panel_1.add(lblNpuzzleSolver, gbc_lblNpuzzleSolver);
		
		JSlider slider = new JSlider();
		contentPane.add(slider, BorderLayout.SOUTH);
		
	}
	
}