console.log('script.js loaded');

window.addEventListener('load', function(e) {
	console.log('DOM loaded');
	init();
});

function init(e) {
	console.log('in init()');
	document.getElementById('editBookDiv').style.display = 'none';

	document.bookForm.lookup.addEventListener('click', function(event) {
		event.preventDefault();
		let bookId = document.bookForm.bookId.value;
		if (!isNaN(bookId) && bookId > 0) {
			getBook(bookId);
		}
	});
	loadBooks();
	let newBookButton = document.getElementById("newFilmButton");
	newBookButton.addEventListener('click', function(e) {
		e.preventDefault();
		let newBook = {
			title: newBookForm.newBookTitle.value,
			releaseDate: newBookForm.newBookReleaseDate.value,
			price: newBookForm.newBookPrice.value,
			startDate: newBookForm.newBookDateStarted.value,
			finishDate: newBookForm.newBookFinished.value,
			numberOfPages: newBookForm.newBookPageCount.value,
			finished: newBookForm.newBookFinished.value
		}
		createBook(newBook);
	});

	let cancelEditButton = document.getElementById("cancelEditButton");
	cancelEditButton.addEventListener('click', function(e) {
		e.preventDefault();
		showHome();
	});

	let updateButton = document.getElementById('saveFilmButton');
	updateButton.addEventListener('click', function(e) {
		e.preventDefault();
		const updatedBook = {
			id: parseInt(editBookForm.editBookId.value, 10),
			title: editBookForm.title.value,
			releaseDate: editBookForm.bookReleaseDate.value,
			dateStarted: editBookForm.dateStartedBook.value,
			dateFinished: editBookForm.dateFinishedBook.value,
			pageCount: editBookForm.bookPageCount.value,
			finished: editBookForm.bookFinished.value,
		};
		updateBook(updatedBook);
	});
}

function showHome() {
	document.getElementById('editBookDiv').style.display = 'none';
	document.getElementById('newBookFormDiv').style.display = 'block';
	document.getElementById('bookListDiv').style.display = 'block';
}

function loadBooks() {
	let xhr = new XMLHttpRequest();
	let url = 'api/books';
	xhr.open('GET', url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let books = JSON.parse(xhr.responseText);
				displayBooks(books);
				console.log(books);
				showPagesRead(books);
			}
			else {

			}
		}
	};
	xhr.send();
}

function displayBooks(books) {
	let tbody = document.getElementById('bookTableBody');
	tbody.textContent = '';
	if (!Array.isArray(books)) {
		return;
	}
	for (let book of books) {
		let tr = document.createElement('tr');
		tr.class = 'bookInfoRow';
		tbody.appendChild(tr);
		let td = document.createElement('td');
		td.textContent = book.title;
		td.class = 'bookTitleData';
		tr.appendChild(td);
		let authorNames = book.authors.map(author => `${author.firstName} ${author.lastName}`);
		let td2 = document.createElement('td');
		td2.textContent = authorNames.join(', ');
		tr.appendChild(td2);
		let editButton = document.createElement('button');
		editButton.name = "editButton";
		editButton.value = book.id;
		editButton.textContent = "Edit Book";
		editButton.addEventListener("click", function(e) {
			e.preventDefault();
			showEditForm(book.id);
		})
		tr.appendChild(editButton);
		let deleteButton = document.createElement('button');
		deleteButton.name = "deleteButton";
		deleteButton.value = book.id;
		deleteButton.textContent = "Delete Book";
		deleteButton.class = "btn btn-warning"
		deleteButton.type = "button";
		deleteButton.id = "deleteButton";
		deleteButton.addEventListener('click', function(e) {
			e.preventDefault();
			deleteBook(book.id);
		});
		tr.appendChild(deleteButton);
	}
}

function getBook(bookId) {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/books/' + bookId, true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let data = xhr.responseText;
				console.log(data);
				let book = JSON.parse(data);
				console.log(book.title);
				displayBook(book);
			}
			else {
				document.getElementById('bookData').textContent = 'Book not found';
				console.log('Error making request: ' + xhr.status);
			}
		}
	}
	xhr.send();
}

function displayBook(book) {
	let dataDiv = document.getElementById('bookData');
	dataDiv.textContent = '';
	let title = document.createElement('h1');
	title.textContent = "Title: " + book.title;
	dataDiv.appendChild(title);
	if (book.authors && book.authors.length >= 1) {
		let authors = document.createElement('h2');
		let authorNames = book.authors.map(author => `${author.firstName} ${author.lastName}`);
		authors.textContent = "Authors: " + authorNames.join(", ");
		dataDiv.appendChild(authors);
	}
	let ul = document.createElement('ul');
	dataDiv.appendChild(ul);
	let li = document.createElement('li');
	li.textContent = "Release Date: " + book.releaseDate;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = "Price: $" + book.price;
	ul.appendChild(li);
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = "Pages: " + book.numberOfPages;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = "Date Started Reading: " + book.dateStarted;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = "Date Finished Reading: " + book.dateFinished;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = "Finished Book?: " + book.finished;
	ul.appendChild(li);
}

function createBook(newBook) {
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/books', true);
	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				let data = JSON.parse(xhr.responseText);
				console.log(data);
				displayBook(newBook);
				loadBooks();

			}
			else {
				console.error("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};

	let newBookJSON = JSON.stringify(newBook);

	xhr.send(newBookJSON);
}

function deleteBook(bookId) {
	let url = 'api/books/' + bookId;
	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 204) {
				loadBooks();
			} else {
				console.error("DELETE request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	xhr.send();
}


function showEditForm(bookId) {
	const url = 'api/books/' + bookId;
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				const book = JSON.parse(xhr.responseText);
				document.getElementById('editBookDiv').style.display = 'block';
				document.getElementById('newBookFormDiv').style.display = 'none';
				document.getElementById('bookListDiv').style.display = 'none';
				editBookForm.editBookId.value = book.id;
				editBookForm.bookTitle.value = book.title;
				editBookForm.bookReleaseDate.value = book.releaseDate;
				editBookFormd.dateStartedBook.value = book.dateStarted;
				editBookFormd.dateFinishedBook.value = book.dateFinished;
				editBookForm.bookPageCount.value = book.pageCount;
				editBookForm.bookFinished.value = book.finished;
			} else {
				console.error("GET request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	xhr.send();
}

function updateBook(book) {
	const url = 'api/books/' + book.id;
	const xhr = new XMLHttpRequest();
	xhr.open('PUT', url);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				displayBook(book.id);
				showHome();
			} else {
				console.error("PUT request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	xhr.send(JSON.stringify(book));
}

function showPagesRead(books) {
	let pageCount = 0;
	let pageCountDiv = document.getElementById('pageCountReadDiv');
	for (let book of books) {
		pageCount += book.numberOfPages;
	}
	let h4 = document.createElement('h4');
	h4.textContent = pageCount + " total pages read.";
	pageCountDiv.appendChild(h4);
	
}


