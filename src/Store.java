import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Store implements StoreCapable {
	private String filename = "xmlflie.xml";

	protected abstract void storeProduct(Product product);

	public abstract ArrayList<Product> getallProduct();

	@Override
	public void storeCDProduct(String name, int price, int size) {
		Product cd = createProduct("CD", name, price, size);
		store(cd);

	}

	@Override
	public void storeBookProduct(String name, int price, int size) {
		Product book = createProduct("Book", name, price, size);
		store(book);

	}

	public void saveToXml(Product product) {
		File xmlFile = new File(filename);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc;
			Element rootElement;
			if (xmlFile.exists()) {
				doc = builder.parse(xmlFile);
				rootElement = doc.getDocumentElement();
			} else {
				doc = builder.newDocument();
				rootElement = doc.createElement("Products");
				doc.appendChild(rootElement);
			}
			Element elem = doc.createElement("Product");
			rootElement.appendChild(elem);
			Attr name = doc.createAttribute("Name");
			elem.setAttributeNode(name);
			name.setValue(product.getName());
			Attr price = doc.createAttribute("Price");
			elem.setAttributeNode(price);
			price.setValue(product.getPrice().toString());
			Attr productType = doc.createAttribute("Type");
			if(product instanceof CDProduct){
				elem.setAttributeNode(productType);
				productType.setValue("CD");
			}
			if(product instanceof BookProduct){
				elem.setAttributeNode(productType);
				productType.setValue("Book");
				
			}
			
			TransformerFactory transformerF = TransformerFactory.newInstance();
			Transformer transformer = transformerF.newTransformer();
			DOMSource domSource = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlFile.getName()));
			transformer.transform(domSource, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Product createProduct(String type, String name, int price, int size) {
		Product product = null;
		if (type.equals("CD")) {
			product = new CDProduct(name, price, size);

		} else if (type.equals("Book")) {
			product = new BookProduct(name, price, size);
		}
		return product;
	}

	public ArrayList<Product> loadProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filename);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Product");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String name = ((Element) node).getAttribute("Name");
				String value = ((Element) node).getAttribute("Price");
				String ProductType = ((Element) node).getAttribute("Type");
				int price = Integer.parseInt(value);
				Product product = createProduct(ProductType, name, price, 0);
				products.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return products;
	}

	public void store(Product product) {
		storeProduct(product);
		saveToXml(product);
	}

}
