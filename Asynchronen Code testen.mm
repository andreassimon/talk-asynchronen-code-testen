<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1395865538302" ID="ID_1002445127" MODIFIED="1413301713550" TEXT="Asynchronen Code testen">
<node CREATED="1412698129767" ID="ID_924084005" MODIFIED="1413301686286" POSITION="right" TEXT="Verteilte Systeme"/>
<node CREATED="1412266276766" ID="ID_1249199303" MODIFIED="1412266285728" POSITION="right" TEXT="Was bedeutet asynchron">
<node CREATED="1412266286827" ID="ID_1545939731" MODIFIED="1412266289637" TEXT="vs.">
<node CREATED="1412266291030" ID="ID_267463380" MODIFIED="1412266294446" TEXT="Asynchron">
<node CREATED="1412696481331" LINK="http://de.wikipedia.org/wiki/Asynchrone_Kommunikation" MODIFIED="1412696481331" TEXT="de.wikipedia.org &gt; Wiki &gt; Asynchrone Kommunikation"/>
<node CREATED="1412697242732" ID="ID_314433280" MODIFIED="1412697246841" TEXT="Implementierungen">
<node CREATED="1412697251385" ID="ID_677989776" MODIFIED="1412697257125" TEXT="Select / poll Schleifen"/>
<node CREATED="1412697259799" ID="ID_1891917181" MODIFIED="1412697264734" TEXT="Callback-Funktionen"/>
<node CREATED="1412697269576" ID="ID_1881350248" MODIFIED="1412697275925" TEXT="Light-Weight Processes"/>
</node>
<node CREATED="1412697287700" ID="ID_673512326" LINK="http://de.wikipedia.org/wiki/Nicht-blockierende_Synchronisation" MODIFIED="1412697355645" TEXT="Nicht-blockierende Synchronisation"/>
</node>
<node CREATED="1412266294930" ID="ID_1690460713" MODIFIED="1412266298286" TEXT="Concurrent">
<node CREATED="1412697569298" ID="ID_263075287" MODIFIED="1412697582055" TEXT="Folgt &apos;Concurrent&apos; aus &apos;asynchron&apos;?"/>
<node CREATED="1412697699926" ID="ID_1818641899" MODIFIED="1412697708240">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Akka:
    </p>
    <p>
      Akka is a toolkit and runtime for building highly concurrent, distributed, and fault tolerant event-driven applications on the JVM.
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1412266298519" ID="ID_1192565979" MODIFIED="1412266300439" TEXT="Parallel"/>
</node>
</node>
<node CREATED="1413320141880" ID="ID_537325224" MODIFIED="1413320144585" POSITION="right" TEXT="Listening">
<node CREATED="1395865609609" ID="ID_228361039" MODIFIED="1413320632893" TEXT="Synchronisierungsmechanismen">
<node CREATED="1395865619730" ID="ID_764051077" MODIFIED="1395865624118" TEXT="Latches"/>
<node CREATED="1395865629922" ID="ID_962109559" MODIFIED="1395865632046" TEXT="Monitore">
<node CREATED="1412853556908" ID="ID_1382022293" MODIFIED="1412853564619" TEXT="implizit bei jedem Objekt"/>
<node CREATED="1413301694981" ID="ID_1604583915" MODIFIED="1413307564390" TEXT="Java: synchronized"/>
</node>
<node CREATED="1395865624548" ID="ID_1434193426" MODIFIED="1395865629444" TEXT="Semaphore"/>
<node CREATED="1413307565504" ID="ID_1734369002" MODIFIED="1413307566961" TEXT="Java">
<node CREATED="1395865633832" ID="ID_1336494284" MODIFIED="1395865642361" TEXT="Thread-sichere Datenstrukturen"/>
</node>
</node>
</node>
<node CREATED="1413320145097" ID="ID_970823525" MODIFIED="1413320156247" POSITION="right" TEXT="Sampling"/>
<node CREATED="1396439640989" ID="ID_873677998" MODIFIED="1396439694368" POSITION="right">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Hilfreiche Fehlermeldungen
    </p>
    <p>
      <font size="1">Also nicht: </font>
    </p>
    <p>
      <font size="1">java.lang.Exception: test timed out after 500 milliseconds </font>
    </p>
    <p>
      <font size="1">at sun.misc.Unsafe.park(Native Method) </font>
    </p>
    <p>
      <font size="1">at java.util.concurrent.locks.LockSupport.park(LockSupport.java:186) </font>
    </p>
    <p>
      <font size="1">at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:834) </font>
    </p>
    <p>
      <font size="1">at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireSharedInterruptibly(AbstractQueuedSynchronizer.java:994) </font>
    </p>
    <p>
      <font size="1">at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireSharedInterruptibly(AbstractQueuedSynchronizer.java:1303) </font>
    </p>
    <p>
      <font size="1">at java.util.concurrent.CountDownLatch.await(CountDownLatch.java:236) </font>
    </p>
    <p>
      <font size="1">at java_util_concurrent_CountDownLatch$await.call(Unknown Source) </font>
    </p>
    <p>
      <font size="1">at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:45) </font>
    </p>
    <p>
      <font size="1">at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:108) </font>
    </p>
    <p>
      <font size="1">at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:112) </font>
    </p>
    <p>
      <font size="1">at de.oneos.eventstore.inmemory.InMemoryEventStoreTest.observe__should_send_EventEnvelopes_to_subscribed_Observers(InMemoryEventStoreTest.groovy:46) </font>
    </p>
    <p>
      <font size="1">at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) </font>
    </p>
    <p>
      <font size="1">at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) </font>
    </p>
    <p>
      <font size="1">at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) </font>
    </p>
    <p>
      <font size="1">at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47) </font>
    </p>
    <p>
      <font size="1">at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12) </font>
    </p>
    <p>
      <font size="1">at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44) </font>
    </p>
    <p>
      <font size="1">at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17) </font>
    </p>
    <p>
      <font size="1">at org.junit.internal.runners.statements.FailOnTimeout$StatementThread.run(FailOnTimeout.java:74)</font>
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1395866436809" ID="ID_1217545927" MODIFIED="1395866447294" POSITION="right" TEXT="Fehlerwahrscheinlichkeiten erh&#xf6;hen">
<node CREATED="1395866448987" ID="ID_50702965" MODIFIED="1395866458575" TEXT="L&#xe4;ngere Inputlisten?"/>
<node CREATED="1395866459131" ID="ID_724305202" MODIFIED="1395866470866" TEXT="Mehrere Threads?"/>
<node CREATED="1395866471638" ID="ID_798342058" MODIFIED="1395866476851" TEXT="Bestimmte Scheduler?"/>
</node>
<node CREATED="1395865647408" ID="ID_173020837" MODIFIED="1395865659652" POSITION="right" TEXT="Multi-Threaded Code testen">
<node CREATED="1395865661182" ID="ID_1892705553" MODIFIED="1395865668623" TEXT="Testen auf Race Conditions"/>
</node>
<node CREATED="1412613605626" ID="ID_1332829226" MODIFIED="1412613606959" POSITION="left" TEXT="user-experience consequences of asynchronous communications"/>
<node CREATED="1400766505139" ID="ID_952850813" LINK="http://www.heise.de/developer/artikel/Asynchron-testen-Mocha-Co-2191178.html?wt_mc=rss.developer.beitrag.atom" MODIFIED="1412784988933" POSITION="right" TEXT="Asynchron testen: Mocha &amp; Co.">
<node CREATED="1412784825865" LINK="https://www.npmjs.org/package/async" MODIFIED="1412784825865" TEXT="https://www.npmjs.org/package/async"/>
</node>
<node CREATED="1412266962851" ID="ID_1575381875" MODIFIED="1412266970062" POSITION="left" TEXT="Java Concurrency in Practice">
<node CREATED="1412266999623" ID="ID_1076395994" MODIFIED="1412267007239" TEXT="Chapter 12 - Testing Concurrent Programs"/>
</node>
<node CREATED="1412267012475" ID="ID_783689131" MODIFIED="1412267023540" POSITION="left" TEXT="Growing Object-Oriented Software"/>
<node CREATED="1412266245463" ID="ID_87071515" MODIFIED="1412266249922" POSITION="left" TEXT="Performance"/>
<node CREATED="1413202026478" ID="ID_122492125" LINK="http://www.planetgeek.ch/2009/08/25/how-to-find-a-concurrency-bug-with-java/" MODIFIED="1413202032814" POSITION="left" TEXT=" How to find a concurrency bug with java? "/>
<node CREATED="1408477747196" ID="ID_499917985" LINK="http://www.infoq.com/articles/Hunting-Concurrency-Bugs-1?utm_campaign=infoq_content&amp;utm_source=infoq&amp;utm_medium=feed&amp;utm_term=global&amp;utm_reader=feedly" MODIFIED="1408477753395" POSITION="left" TEXT="Hunting Java Concurrency Bugs"/>
<node CREATED="1412758857375" ID="ID_970527024" LINK="http://robots.thoughtbot.com/write-reliable-asynchronous-integration-tests-with-capybara?utm_source=rubyweekly&amp;utm_medium=email" MODIFIED="1412758862938" POSITION="left" TEXT="Write Reliable, Asynchronous Integration Tests With Capybara"/>
<node CREATED="1413301672730" ID="ID_1239879732" MODIFIED="1413301677793" POSITION="left" TEXT="Discarded">
<node CREATED="1412785741999" ID="ID_1471518270" MODIFIED="1412785749557" TEXT="java.util.nio"/>
<node CREATED="1396439011019" ID="ID_1750925083" MODIFIED="1396439023126" TEXT="Synchronen Code in Asynchronen Code transformieren"/>
</node>
<node CREATED="1395865990291" ID="ID_80436748" MODIFIED="1395866002666" POSITION="left" TEXT="AAA-Pattern vs. Callback-Code"/>
<node CREATED="1395865594691" ID="ID_266511826" MODIFIED="1395865607941" POSITION="left" TEXT="Synchrone vs. Asynchrone Testframeworks"/>
<node CREATED="1413475527949" ID="ID_93812594" MODIFIED="1413477325961" POSITION="right" TEXT="Feedback XP-Days 2014">
<font BOLD="true" NAME="SansSerif" SIZE="12"/>
<node COLOR="#338800" CREATED="1413477328104" ID="ID_797345181" MODIFIED="1413477792844" TEXT="7 Gr&#xfc;ne">
<node CREATED="1413477406054" ID="ID_1015541349" MODIFIED="1413477441936">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;- Interessant,&#160;&#252;berraschend komplex
    </p>
    <p>
      &#160;- In der kurzen Zeit kann man nicht in den Code &quot;abtauchen&quot;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1413477446051" ID="ID_80825189" MODIFIED="1413477517063">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;+ Sch&#246;ner Code
    </p>
    <p>
      &#160;- Mit dem pl&#246;tzlichen Einbau von RabbitMQ wurden glaube ich viele Leute &#252;berrascht und abgeh&#228;ngt
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1413477518582" ID="ID_1827697409" MODIFIED="1413477604231">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Listening und Sampling gut mit Beispielen vorgestellt
    </p>
    <p>
      &#160;- Beispiele k&#246;nnten weniger komplex sein
    </p>
    <p>
      &#160;- NodeJS k&#246;nntegenauer vorgestellt oder *weggelassen* werden, habe den Zusammenhang zu NodeJS bzw. die Unterschiede nicht verstanden
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node COLOR="#cc6600" CREATED="1413477331782" ID="ID_1080214237" MODIFIED="1413477813847" TEXT="12 Gelbe">
<font NAME="SansSerif" SIZE="12"/>
<node CREATED="1413477611444" ID="ID_68369193" MODIFIED="1413477801613" TEXT="Y U NO UNIT TEST"/>
<node CREATED="1413477624650" ID="ID_765364872" MODIFIED="1413477801613">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      einfacheren Beispielcode, griffigere L&#246;sung
    </p>
    <p>
      -&gt; &quot;Last aufs System um Wahrscheinlichkeit zu verringern&quot;
    </p>
    <p>
      =&gt; &quot;unbefriedigende&quot; L&#246;sung
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1413477677022" ID="ID_1230049825" MODIFIED="1413477801614">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Guter Vortrag, interessantes Thema &amp; Ans&#228;tze, ABER:
    </p>
    <p>
      Lastbasierende Tests sind *sehr* schlecht, da nicht 100% zuverl&#228;ssig.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1413477721014" ID="ID_504264930" MODIFIED="1413477801615" TEXT="Der Vortrag war zu oberfl&#xe4;chlich. Das Thema war aber sehr interessant."/>
</node>
</node>
<node CREATED="1414142587805" ID="ID_490466792" LINK="http://www.sustainabletdd.com/2014/10/tdd-and-asychronous-behavior-part-1.html" MODIFIED="1414142587805" POSITION="left" TEXT="sustainabletdd.com &gt; 2014 &gt; 10 &gt; Tdd-and-asychronous-behavior-part-1"/>
<node CREATED="1414142593477" ID="ID_260170970" LINK="http://www.sustainabletdd.com/2014/10/tdd-and-asychronous-behavior-part-2.html" MODIFIED="1414142593477" POSITION="left" TEXT="sustainabletdd.com &gt; 2014 &gt; 10 &gt; Tdd-and-asychronous-behavior-part-2"/>
</node>
</map>
