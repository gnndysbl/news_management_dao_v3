package by.study.news.controller;
import java.util.HashMap;

import java.util.Map;
import by.study.news.controller.impl.AddArticle;
import by.study.news.controller.impl.GoToAddArticlePage;
import by.study.news.controller.impl.DeleteArticle;
import by.study.news.controller.impl.DeleteMarked;
import by.study.news.controller.impl.GoToEditArticlePage;
import by.study.news.controller.impl.GoToNewsListPage;
import by.study.news.controller.impl.EditArticle;
import by.study.news.controller.impl.GoToArticlePage;
import by.study.news.controller.impl.GoToRegistrationPage;


public final class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();
	
	private Map<CommandName, Command> commands = new HashMap<>();
	
	private CommandProvider() {
		commands.put(CommandName.NEWS_LIST, new GoToNewsListPage());
		commands.put(CommandName.VIEW_ARTICLE, new GoToArticlePage());
		commands.put(CommandName.EDIT_ARTICLE_PAGE, new GoToEditArticlePage());
		commands.put(CommandName.DELETE_ARTICLE, new DeleteArticle());
		commands.put(CommandName.DELETE_MARKED, new DeleteMarked());
		commands.put(CommandName.ADD_ARTICLE_PAGE, new GoToAddArticlePage());
		commands.put(CommandName.ADD_ARTICLE, new AddArticle());
		commands.put(CommandName.EDIT_ARTICLE, new EditArticle());
		
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());


	}
		
	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		return commands.get(commandName);
	}
		
	public static CommandProvider getInstance() {
		return instance;
	}

}
