package com.zhangyangjixu.eclipse.plugins.console;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.zhangyangjixu.eclipse.plugins.console"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private static MessageConsole exampleConsole;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/**
	 * Create Example Console
	 * @param name
	 * @return
	 */
	private MessageConsole createSimulabConsole() {
		IConsoleManager conMan = ConsolePlugin.getDefault().getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++) {
			if ("Example Console".equalsIgnoreCase(existing[i].getName()))
				return (MessageConsole) existing[i];
		}
		MessageConsole myConsole = new MessageConsole("Example Console", null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}
	
	/**
	 * 向控制台输出信息，颜色为默认
	 * @param message 输出信息
	 */
	public void writeMessage(String message) {
		MessageConsole myConsole = getDefaultConsole();

		MessageConsoleStream out = myConsole.newMessageStream();
		try {
			IConsoleView view = (IConsoleView) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.showView("org.eclipse.ui.console.ConsoleView");
			view.display(getDefault().getDefaultConsole());
			out.println(message);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  向控制台输出信息，颜色为SWT.COLOR_BLUE
	 * @param message
	 */
	public void writeBLUEMessage(String message) {
		MessageConsole myConsole = getDefaultConsole();

		MessageConsoleStream out = myConsole.newMessageStream();
		try {
			IConsoleView view = (IConsoleView) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.showView("org.eclipse.ui.console.ConsoleView");
			view.display(myConsole);
			Display display = Display.getCurrent();
			out.setColor(display.getSystemColor(SWT.COLOR_BLUE));
			out.println(message);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public MessageConsole getDefaultConsole() {
		if(exampleConsole == null){
			exampleConsole = createSimulabConsole();
		}
		return exampleConsole;
	}
}
