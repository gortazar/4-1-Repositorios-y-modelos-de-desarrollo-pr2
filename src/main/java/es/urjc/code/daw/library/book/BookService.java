package es.urjc.code.daw.library.book;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.togglz.core.manager.FeatureManager;

import es.urjc.code.daw.library.notification.NotificationService;
import es.urjc.code.daw.library.toggle.Features;

/* Este servicio se usará para incluir la funcionalidad que sea 
 * usada desde el BookRestController y el BookWebController
 */
@Service
public class BookService {

	private BookRepository repository;
	private NotificationService notificationService;
	private FeatureManager featureManager;

	public BookService(BookRepository repository, NotificationService notificationService, FeatureManager featureManager) {
		this.repository = repository;
		this.notificationService = notificationService;
		this.featureManager = featureManager;
	}

	public Optional<Book> findOne(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Book> findAll() {
		return repository.findAll();
	}

	public Book save(Book book) {
		if(featureManager.isActive(Features.LINE_BREAKER)) {
			LineBreaker lb = new LineBreaker();
			book.setDescription(lb.breakLine(book.getDescription(), 10));
		}
		Book newBook = repository.save(book);
		notificationService.notify("Book Event: book with title="+newBook.getTitle()+" was created");
		return newBook;
	}

	public void delete(long id) {
		repository.deleteById(id);
		notificationService.notify("Book Event: book with id="+id+" was deleted");
	}
}
