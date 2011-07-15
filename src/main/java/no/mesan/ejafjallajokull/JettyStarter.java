package no.mesan.ejafjallajokull;

import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public final class JettyStarter {

	private static Server SERVER;
	final private ContextHandlerCollection contextHandlerCollection;
	final private DeploymentManager deploymentManager;
	final private WebAppContext context;

	public JettyStarter() {
		SERVER = new Server();
		contextHandlerCollection = new ContextHandlerCollection();
		deploymentManager = new DeploymentManager();
		context = new WebAppContext();
	}

	public void start(final int port) throws Exception {
		init(port);
		SERVER.setHandler(contextHandlerCollection);
		SERVER.start();
		SERVER.join();
	}

	private void init(final int port) {
		initConnectors(port);
		initDeploymentManager();
		initWebApp();
	}

	private void initConnectors(final int port) {
		final Connector connector = new SelectChannelConnector();
		connector.setPort(port);
		SERVER.setConnectors(new Connector[] { connector });
	}

	private void initDeploymentManager() {
		deploymentManager.setContexts(contextHandlerCollection);
		SERVER.addBean(deploymentManager);
	}

	private void initWebApp() {
		context.setContextPath("/");
		context.setWar("src/main/webapp");
		context.setParentLoaderPriority(true);
		// context.setDefaultsDescriptor("src/main/resources/jetty/webdefault.xml");
		contextHandlerCollection.addHandler(context);
	}

	public static void main(final String[] args) throws Exception {
		final JettyStarter starter = new JettyStarter();
		starter.start(8080);
	}

	public void stop() throws Exception {
		SERVER.stop();
	}
}