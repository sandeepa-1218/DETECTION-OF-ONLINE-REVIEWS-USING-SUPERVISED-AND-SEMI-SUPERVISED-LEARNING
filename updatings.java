package com.Servlets;
import java.io.IOException; import java.io.PrintWriter; import java.sql.ResultSet; import java.sql.SQLException; import java.util.ArrayList; import java.util.Iterator; import java.util.List;
import java.util.Map; import java.util.Properties;
import java.util.StringTokenizer; import javax.servlet.RequestDispatcher; import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 
import javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpServletResponse; import com.DBConnect.DbConnection; import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation; import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation; import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation; import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation; import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation; import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP; import edu.stanford.nlp.semgraph.SemanticGraph; import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependen ciesAnnotation;
import edu.stanford.nlp.trees.Tree;
 
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation; import edu.stanford.nlp.util.CoreMap;
/**
* Servlet implementation class Updating

*/ @WebServlet("/Updating")
public class Updating extends HttpServlet {

private static final long serialVersionUID = 1L;

/**

* @see HttpServlet#HttpServlet()

*/

public Updating() { super();
// TODO Auto-generated constructor stub
}

/**

*	@see	HttpServlet#doGet(HttpServletRequest	request,	HttpServletResponse response)

*/

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
// TODO Auto-generated method stub

String	neg	= "anger,avoid,awful,ashamed,annoy,abandon,abuse,afraid,alone,attack,alcohol,beg,bore,"
+
"bad,broken,blame,beer,booze,bomb,cannot,clumsy,confuse,cheat,delay,danger,difficult,"

+
"dislike,defeat,dead,damage,deny,depress,drug,dirty,dishonest,damage,divorce,disease,"

+
"dreadful,disaster,dumb,evil,end,excusembarrass,enemy,fear,fight,furious,fault,fail,"

+
"failure,foul,fright,force,false,gossip,greed,guilty,hate,hurt,hide,hunger,horrible,"

+
"harm,harmful,humiliate,impossible,ignore,insecure,ill,insane,inferior,insult,jealous,"
+
"kill,lie,lost,loose,miser,no,not,never,offensive,pain,pessimist,problem,poor,poison,"

+ "quit,reject,revenge,rude,sad,sorry,"

+ "sorrow,steal,suspicious,suspect,traitor,tension,ugly,upset,un-
fair,worthless,worst";

Properties props = new Properties(); String hid=request.getParameter("hid");
String[] food = { "chicken", "pizza", "roti", "food","teast","kabab"}; String[] atm = { "atmosphere", "ac", "wether" };
String[] work = { "servent", "service", "work" };
 
List<String> res_food = new ArrayList<String>(); List<String> res_atm = new ArrayList<String>(); List<String> res_work = new ArrayList<String>();
props.put("annotators","tokenize, ssplit, pos, lemma, ner, parse, dcoref"); StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
String text=""; try {
ResultSet r=DbConnection.getReviews(); while(r.next())
{

if(r.getString(2).equals(hid))

{

text=text+"."+r.getString(5);

}

}

} catch (SQLException e) {

// TODO Auto-generated catch block e.printStackTrace();
}
//= "The food was teasty. The service was worst. The atmosphere was concern
. chicken was bad.";
 
int foodcount = 0, workcount = 0, atmcount = 0; Annotation document = new Annotation(text); pipeline.annotate(document);
List<CoreMap> sentences = document.get(SentencesAnnotation.class); for (CoreMap sentence : sentences) {
for (CoreLabel token : sentence.get(TokensAnnotation.class)) { String word = token.get(TextAnnotation.class);
String pos = token.get(PartOfSpeechAnnotation.class); String ne = token.get(NamedEntityTagAnnotation.class); String lemm = token.get(LemmaAnnotation.class); System.out.println("word: " + word + " pos: " + pos + " ne:"
+ ne + " lemma: " + lemm);
}
Tree tree = sentence.get(TreeAnnotation.class);
System.out.println("parse tree:\n" + tree); SemanticGraph dependencies =
sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);

System.out.println("dependency graph:\n"+ dependencies.toDotFormat());
for (SemanticGraphEdge edge1 : dependencies.edgeIterable()) { int headIndex = edge1.getGovernor().index();
 
int depIndex = edge1.getDependent().index();

int weight = 1; // "edge weight"-- should it be the foodcount = 0;
workcount = 0;

atmcount = 0;

if (edge1.getRelation().equals("nsubj")) { System.out.println("weight :" + edge1.getRelation()); String gov = edge1.getSource().word();
String dest = edge1.getTarget().word(); for (String s : food) {
if (s.equals(dest)) {

res_food.add(gov);

}

}

for (String s : atm) {

if (s.equals(dest)) {

res_atm.add(gov);

}

}

for (String s : work) {
 
if (s.equals(dest)) {

res_work.add(gov);

}

}

}

}

System.out.println(dependencies.edgeCount()); StringTokenizer st = new StringTokenizer(neg, ","); while (st.hasMoreElements()) {
String w = (String) st.nextElement(); Iterator<String> i = res_food.iterator(); while (i.hasNext()) {
if (i.next().equals(w)) {

foodcount++;

}

}

i = res_work.iterator(); while (i.hasNext()) {
if (i.next().equals(w)) {
workcount++;
 
}

}

i = res_atm.iterator(); while (i.hasNext()) {
if (i.next().equals(w)) {
atmcount++;

}

}

}

}

System.out.println("FOOD :" + res_food); int f=0;int w=0;int a=0; if(res_food.size()==0)
{ f=100;
}else{

f=((res_food.size()-foodcount)*100)/res_food.size();

}

System.out.println("WORK :" + res_work); if(res_work.size()==0)
 
{ w=100;
}else{
w=((res_work.size()-workcount)*100)/res_work.size();

}

System.out.println("ATM" + res_atm); if(res_atm.size()==0)
{ a=100;
}else{

a=((res_atm.size()-atmcount)*100)/res_atm.size();

}

int tot=(f+w+a)/3;



response.sendRedirect("graphic.jsp?f="+f+"&&w="+w+"&&a="+a+"&&fc="+res_fo od.size()+"&&wc="+res_work.size()+"&&ac="+res_atm.size()+"&&tot="+tot);

}

/**

*	@see	HttpServlet#doPost(HttpServletRequest	request,	HttpServletResponse response)
*/
 
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// TODO Auto-generated method stub

}

}

