
/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.mongo;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableAutoConfiguration
@ComponentScan
@Controller
public class SampleMongoApplication  extends WebMvcConfigurerAdapter{
	
	
	@PostConstruct
	private void init(){
		this.repository.deleteAll();

		// save a couple of customers
		this.repository.save(new Book("Alice", "One day in November", "A really borring book"));
		this.repository.save(new Book("Alice", "Another day in November", "A really borring book"));
		this.repository.save(new Book("Bob", "September the third", "Thrilling story about a rock"));
		this.repository.save(new Book("Edouard", "The day I ate an apple", "The best story I'll ever read in my life. Some magazine"));
		this.repository.save(new Book("Camille", "I thought about writing a book", "Will Camille write the book ? You'll know it if you read it... Story full of suspens !"));

	}
	@Autowired
	private BookRepository repository;

	@RequestMapping("/")
	public String index(Map<String, Object> model){
		System.out.println(this.repository.findBySummary("A really"));
		return "index";
	}
	@RequestMapping(value="/books")
	public @ResponseBody List<Book> getListOfBooks() {
		List<Book> books = this.repository.findAll();
		return books;
	}
	
	@RequestMapping(value="/book/{author}")
	public @ResponseBody List<Book> getBookFromAuthor(@PathVariable String author){
		List<Book> books = this.repository.findByAuthor(author);
		return books;
	}
	
	@RequestMapping(value="/book/delete/{id}")
	public @ResponseBody boolean deleteABook(@PathVariable String id){
		this.repository.delete(id);
		return true;
	}
	
	@RequestMapping(value="/book/add/{author}/{summary}/{title}")
	public @ResponseBody Book addABook (@PathVariable String author, @PathVariable String summary, @PathVariable String title ){
		return this.repository.save(new Book(author, title, summary));
	}
	
	@RequestMapping(value="/book/update")
	public @ResponseBody Book updateABook (@RequestBody Book book  ){
		/*Update update = new Update();
		update.set("author", author);
		update.set("summary", summary);
		update.set("title", title);
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		query.fields().include("name");
		this.repository.updateFirst(query, update, Book.class);*/
		System.out.println(book);
		Book delete = this.repository.findById(book.getId());
		this.repository.delete(delete);
		
		return this.repository.save(book);
	}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleMongoApplication.class, args);
	}
}
