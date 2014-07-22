// $ANTLR 3.4 C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2014-07-22 18:14:13

package com.vw.lang.grammar;

// Exceptions
import java.lang.Throwable;

// builder
import com.vw.lang.processor.model.builder.VWMLModelBuilder;
import com.vw.lang.processor.model.builder.VWMLModuleInfo;
import com.vw.lang.processor.context.builder.VWMLContextBuilder;
import com.vw.lang.processor.context.builder.VWMLContextBuilder.ContextBunch;
import com.vw.lang.processor.context.builder.VWMLContextBuilder.ContextBunchElement;

// general code generator
import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.ICodeGenerator.StartModuleProps;
import com.vw.lang.sink.utils.ComplexEntityNameBuilder;
import com.vw.lang.sink.utils.EntityWalker;
import com.vw.lang.sink.utils.EntityWalker.EntityDescriptor;
import com.vw.lang.sink.utils.EntityWalker.ComplexContextDescriptor;
import com.vw.lang.sink.utils.GeneralUtils;

// specific code generator
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.java.code.JavaCodeGenerator;
import com.vw.lang.sink.java.code.JavaCodeGenerator.JavaModuleStartProps;

// logger
import org.apache.log4j.Logger;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
---------------------- Virtual World's language grammar ------------------------------------

*/
@SuppressWarnings({"all", "warnings", "unchecked"})
public class VirtualWorldModelingLanguageParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "C", "COMMA", "COMMENT", "CPP", "DQUOTE", "IAS", "ID", "JAVA", "LETTER", "LIFETERM", "LINE_COMMENT", "NATIVE_CODE", "OBJECTIVEC", "OPACTIVATECTX", "OPACTIVATEONFRINGE", "OPAPPLYTOCONTEXT", "OPBEGIN", "OPBORN", "OPBREAKPOINT", "OPCALLP", "OPCARTESIAN", "OPCLONE", "OPCREATEEXPR", "OPDYNCONTEXT", "OPENDCONFLICTGROUP", "OPEQ", "OPEXECUTE", "OPFIND", "OPFIRST", "OPFOREACH", "OPGET", "OPIDENT", "OPIN", "OPINCL", "OPINTERPRET", "OPINTERRUPT", "OPINTERSECT", "OPJOIN", "OPLAST", "OPPROJECTION", "OPRANDOM", "OPRELAX", "OPREST", "OPSIZE", "OPSQU", "OPSTARTCONFLICTGROUP", "OPSUBSTRUCT", "SEMICOLON", "STRING_LITERAL", "WS", "'('", "')'", "'.'", "'='", "'author'", "'beyond'", "'class'", "'conflictring'", "'conflicts'", "'data'", "'description'", "'entity_history_size'", "'fringe'", "'include'", "'language'", "'module'", "'options'", "'package'", "'path'", "'project_name'", "'source'", "'visualizer'", "'{'", "'}'"
    };

    public static final int EOF=-1;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int C=4;
    public static final int COMMA=5;
    public static final int COMMENT=6;
    public static final int CPP=7;
    public static final int DQUOTE=8;
    public static final int IAS=9;
    public static final int ID=10;
    public static final int JAVA=11;
    public static final int LETTER=12;
    public static final int LIFETERM=13;
    public static final int LINE_COMMENT=14;
    public static final int NATIVE_CODE=15;
    public static final int OBJECTIVEC=16;
    public static final int OPACTIVATECTX=17;
    public static final int OPACTIVATEONFRINGE=18;
    public static final int OPAPPLYTOCONTEXT=19;
    public static final int OPBEGIN=20;
    public static final int OPBORN=21;
    public static final int OPBREAKPOINT=22;
    public static final int OPCALLP=23;
    public static final int OPCARTESIAN=24;
    public static final int OPCLONE=25;
    public static final int OPCREATEEXPR=26;
    public static final int OPDYNCONTEXT=27;
    public static final int OPENDCONFLICTGROUP=28;
    public static final int OPEQ=29;
    public static final int OPEXECUTE=30;
    public static final int OPFIND=31;
    public static final int OPFIRST=32;
    public static final int OPFOREACH=33;
    public static final int OPGET=34;
    public static final int OPIDENT=35;
    public static final int OPIN=36;
    public static final int OPINCL=37;
    public static final int OPINTERPRET=38;
    public static final int OPINTERRUPT=39;
    public static final int OPINTERSECT=40;
    public static final int OPJOIN=41;
    public static final int OPLAST=42;
    public static final int OPPROJECTION=43;
    public static final int OPRANDOM=44;
    public static final int OPRELAX=45;
    public static final int OPREST=46;
    public static final int OPSIZE=47;
    public static final int OPSQU=48;
    public static final int OPSTARTCONFLICTGROUP=49;
    public static final int OPSUBSTRUCT=50;
    public static final int SEMICOLON=51;
    public static final int STRING_LITERAL=52;
    public static final int WS=53;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public VirtualWorldModelingLanguageParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public VirtualWorldModelingLanguageParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return VirtualWorldModelingLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g"; }



    	public static class VWMLCodeGeneratorRecognitionException extends RecognitionException {
    		public VWMLCodeGeneratorRecognitionException() {
    			super();
    		}
    		
    		public VWMLCodeGeneratorRecognitionException(String message) {
    			initCause(new Throwable(message));
    		}
    	}

    	private VWMLModelBuilder vwmlModelBuilder = VWMLModelBuilder.instance();
    	private VWMLContextBuilder vwmlContextBuilder = VWMLContextBuilder.instance();
    	private VWMLContextBuilder.ContextBunch lastProcessedContextBunch = null;
    	private ICodeGenerator codeGenerator = null;
    	private StartModuleProps modProps = null;
    	private ComplexEntityNameBuilder complexEntityNameBuilderDecl = ComplexEntityNameBuilder.instance();
    	private ComplexEntityNameBuilder complexEntityNameBuilderDef = null;
    	private EntityWalker entityWalker = EntityWalker.instance();
    	private EntityWalker contextWalker = EntityWalker.instance();
    	private EntityWalker.Relation lastProcessedEntity = null;
    	private String activeFringe = null;
    	private String lastDeclaredCreatureId = null;
    	private String lastDeclaredCreatureProps = null;
    	private String lastProcessedComplexEntityId = null;
    	private boolean lastProcessedEntityAsTerm = false;
    	private boolean sourceLifeTermDetectedFlag = false;
    	private boolean conflictDefinitionOnRingStarted = false;
    	private String modName = null;
     	private boolean inDebug = false;
     	private boolean moduleInProgress = false;
     	private List<String> deferredIncludes = new ArrayList<String>();
     	
     	private String lastProcessedIAS = null;
     	
     	private Logger logger = Logger.getLogger(this.getClass());
    	
    	public void setupProps() {
        		if (modProps == null) {
        			if (codeGenerator == null && vwmlModelBuilder.getProjectProps() != null) {
        				codeGenerator = vwmlModelBuilder.getProjectProps().getCodeGenerator();
        			}
    			// instantiating module's properties which will be filled later
    			modProps = (codeGenerator != null) ? codeGenerator.buildProps() : null;
    			// tell to builder reference to module's properties
    			if (vwmlModelBuilder.getProjectProps() == null) {
    				vwmlModelBuilder.setProjectProps(modProps);
    			}
    		}	
    	}

    	public StartModuleProps getModuleProps() {
    		return modProps;
    	}
    	
    	public void setModuleProps(StartModuleProps modProps) {
    		this.modProps = modProps;
    	}
    		
    	protected void setInDebug(boolean inDebug) {
    		this.inDebug = inDebug;
    		vwmlModelBuilder.setDebug(inDebug);		
    	}
    	
    	protected boolean isInDebug() {
    		return this.inDebug;
    	}
    	
    	protected void setActiveFringe(String activeFringe) {
    		this.activeFringe = activeFringe;
    	}
    	
    	protected String getActiveFringe() {
    		return this.activeFringe;
    	}
    	
    	protected void addLastDeclaredCreature(String creatureId) {
    		lastDeclaredCreatureId = creatureId;
    	}
    	
    	protected void addLastDeclaredCreatureProps(String creatureProps) {
    		lastDeclaredCreatureProps = creatureProps;
    	}
    	
    	protected String getLastDeclaredCreature() {
    		return lastDeclaredCreatureId;
    	}
    	
    	protected String getLastDeclaredCreatureProps() {
    		return lastDeclaredCreatureProps;
    	}

    	protected String simpleEntityDeclaration(String id) throws RecognitionException {
        		// means that complex entity's name is being built
        		if (complexEntityNameBuilderDecl.isInProgress()) {
        		        if (logger.isDebugEnabled()) {
        		        	logger.debug("simple entity '" + id + "' is added as part of complex entity");
        		        }
        			complexEntityNameBuilderDecl.addObjectId(id);
        		}
        		else {
        			try {
        				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
        				if (codeGenerator != null) {
        					for(String c = contexts.first(); c != null; c = contexts.next()) {
        						codeGenerator.declareSimpleEntity(id, c);
         		        			if (logger.isDebugEnabled()) {
        		        				logger.debug("simple entity '" + id + "' is declared; context '" + c + "'");
        		        			}
        					}
        				}
        			}
        			catch(Exception e) {
        				rethrowVWMLExceptionAsRecognitionException(e);
        			}
        		}
        		return id;
    	}
    	
    	protected void complexEntityDeclarationPhase1() throws RecognitionException {
        	    	complexEntityNameBuilderDecl.startProgress();
    	    	if (logger.isDebugEnabled()) {
    	    		logger.debug("complex entity declaration process - started");
    	    	}	
    	}
    	
    	protected void complexEntityDeclarationPhase2() throws RecognitionException {
    		complexEntityNameBuilderDecl.stopProgress();
    	    	if (logger.isDebugEnabled()) {
    	    		logger.debug("complex entity declaration process - stopped");
    	    	}			
    	}
    	
    	protected String complexEntityDeclarationPhase3() throws RecognitionException {
                  	String id = complexEntityNameBuilderDecl.build();
                  	complexEntityNameBuilderDecl.clear();
    		try {
    			VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
    			if (codeGenerator != null) {
    				for(String c = contexts.first(); c != null; c = contexts.next()) {
    					codeGenerator.declareComplexEntity(id, null, c);
    				}
    			}    	
    			if (logger.isDebugEnabled()) {
    				logger.debug("complex entity '" + id + "' is declared; contexts '" + contexts + "'");
    				logger.debug("complex entity declaration process - finished");
    			}    	
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}
    		return id;            					
    	}

    	protected void declareAbsoluteContextByIASRelation() throws RecognitionException {
    		// point to check deffered actions on effective context
    		unwindEffectiveContext();
        		// get context's bunch from stack
        		Object bunch = vwmlContextBuilder.peek();
        		entityWalker.markFutureEntityAsIAS(bunch);
        		if (logger.isDebugEnabled()) {
        			logger.debug("Context bunch '" + bunch + "' was marked as IAS - pushed to stack");
        		}
        		if (codeGenerator != null) {
        			VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
        			for(String c = contexts.first(); c != null; c = contexts.next()) {
        				codeGenerator.declareContext(c);
        				if (logger.isDebugEnabled()) {
        					logger.debug("Context '" + c + "' was declared");
        				}
        			}
        		}
    	}

    	protected void handleProcessedAbsoluteContextbyIASRelation() {
        		Object bunch = vwmlContextBuilder.peek();
        		if (lastProcessedEntity != null) {
        			if (logger.isDebugEnabled()) {
        				logger.debug("Context bunch '" + bunch + "' which was marked as IAS - removed from context builder stack");
        			}
        		      	vwmlContextBuilder.pop();
        			if (logger.isDebugEnabled()) {
        				logger.debug("!!!!! '" + vwmlContextBuilder.peek() + "'");
        			}    		      	    		      	
        		}
        		else {
        			if (logger.isDebugEnabled()) {
        				logger.debug("Context bunch '" + bunch + "' which was marked as IAS - stayed at context builder stack");
        			}
        		}
    	}

    	protected String unwindEffectiveContext() throws RecognitionException {
    		ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
    		EntityWalker.EntityDescriptor entityDescr = null;
    		String c = "";
    		// top pushed entity's id should be changed on updated in case if its id is the same
    		EntityWalker.Relation rel = (EntityWalker.Relation)entityWalker.peek();		
    		if (contextDescriptor != null && logger.isDebugEnabled()) {
    			logger.debug("Starting unwinding process of defferred effective context; top pushed entity is '" + rel.getObj() + "'");
    		}
    		while(contextDescriptor != null) {
    			if (entityDescr == null) {
    				entityDescr = (EntityWalker.EntityDescriptor)contextDescriptor.getUserData();
    				if (logger.isDebugEnabled()) {
    					logger.debug("creating effective context for entity descriptor '" + entityDescr + "'");
    				}
    			}
    			// building context
    			VWMLContextBuilder effectiveContextBuilder = (VWMLContextBuilder)contextDescriptor.getVwmlEffectiveContextBuilder();
    			if (effectiveContextBuilder == null) {
    				rethrowVWMLExceptionAsRecognitionException(new Exception("'null' effective context builder encountered during effective context building operation"));
    			}
    			c = effectiveContextBuilder.getEffectiveContext() + "." + c;
    			contextWalker.pop();
    			contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
    		}
    		String newEntityId = null;
    		if (entityDescr != null) {
    			newEntityId = VWMLContextBuilder.buildFullEntityName(c, (String)entityDescr.getId());
    			if (codeGenerator != null) {
    				if (rel != null && rel.getObj().equals(entityDescr.getId())) {
    					if (logger.isDebugEnabled()) {
    						logger.debug("Top pushed entity '" + entityDescr.getId() + "' is changed to '" + newEntityId + "' also");
    					}
    					rel.setObj(newEntityId);
    				}
    				codeGenerator.changeObjectIdToImmidiatly(entityDescr.getId(), newEntityId, (String[])entityDescr.getContexts());
    				if (logger.isDebugEnabled()) {
    					logger.debug("Entity '" + entityDescr.getId() + "' changed to '" + newEntityId + "'; contexts '" + entityDescr.getContexts() + "'");
    				}
    			}
    			if (logger.isDebugEnabled()) {
    				logger.debug("Finished unwinding process of defferred effective context");
    			}
    		}
    		return newEntityId;
    	}

    	protected void addEffectiveContext(Object contextId) {
      		VWMLContextBuilder effectiveContextBuilder = null;
          		ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
          		if (contextDescriptor != null) {
          			contextDescriptor.setAddressingByComplexContextEncountered(true);
          			contextDescriptor.setUserData(null);
          		}
        		effectiveContextBuilder = VWMLContextBuilder.instance();
       		// becomes part of 'complex context'
        		effectiveContextBuilder.addEffectiveContext((String)contextId);
        		// effective context on simple entity is equal '.' operator for complex entity
        		contextWalker.push(ComplexContextDescriptor.build(effectiveContextBuilder, false));
        		if (logger.isDebugEnabled()) {
        			logger.debug("effective context detected '" + contextId + "'");
        		}    			
    	}

    	protected void processComplexContext(EntityWalker.Relation rel) throws RecognitionException {
    		if (codeGenerator != null) {
    			// removes entity from declaration and linkage storage; entity is interpreted as 'complex context'
    			String[] contexts = vwmlContextBuilder.buildContext().asStrings();
    			codeGenerator.removeComplexEntityFromDeclarationAndLinkage(rel, contexts);
    			if (logger.isDebugEnabled()) {
    				logger.debug("entity '" + rel.getObj() + "' removed since it was recognized as complex context");
    			}
    			// throws relation to another entity... the relation is thrown until non-context entity is found
    			if (EntityWalker.REL.ASSOCIATION == rel.getRelation()) {
        				Object fIAS = vwmlContextBuilder.peek();
        				if (logger.isDebugEnabled()) {
        					logger.debug("Object '" + fIAS + "' marked again as IAS");
        				}
        				entityWalker.markFutureEntityAsIAS(fIAS);
    			}
       			// so entity is considered as effective context
       			if (lastProcessedComplexEntityId == null) {
    				rethrowVWMLExceptionAsRecognitionException(new Exception("invalid context; single context indicator '.' detected"));
       			}
       			if (logger.isDebugEnabled()) {
       				logger.debug("part of complex context '" + lastProcessedComplexEntityId + "' detected");
       			}
       			// adds effective context
       			addEffectiveContext(lastProcessedComplexEntityId);
    		}
    	}

    	protected EntityWalker.Relation simpleEntityAssembling(String id) throws RecognitionException {
    		EntityWalker.Relation rel = null;
    		// since entity's id may include '.' we should check correctness of name
        		// if name ends with '.' we can suppouse that this name can be considered as complex entity's effective context
        		ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
        		if (!VWMLContextBuilder.isEffectiveContext(id)) {
        			EntityWalker.Relation relParent = (EntityWalker.Relation)entityWalker.peek();
        			boolean partOfComplexEntity = false;
        			if (relParent != null) {
        				complexEntityNameBuilderDef = (ComplexEntityNameBuilder)relParent.getData();
    	    			if (complexEntityNameBuilderDef.isInProgress()) {
    	    				complexEntityNameBuilderDef.addObjectId(id);
    	    				partOfComplexEntity = true;
    	    			}
        			}
        			// standalone simple entity - chance to unwind defferred effective context
        			if (contextDescriptor != null && (relParent == null || !relParent.isParticipatesInComplexContextBuildingProcess())) {
        				if (contextDescriptor.getUserData() == null) {
        					String[] contexts = vwmlContextBuilder.buildContext().asStrings();
        					contextDescriptor.setUserData(EntityDescriptor.build(id, contexts));
    					String newEntityId = unwindEffectiveContext();
    					if (newEntityId != null) {
    						id = newEntityId;
    					}
    				}
        			}    			
       			rel = buildRelation(id);
        			if (logger.isDebugEnabled()) {
        				logger.debug("processed simple entity '" + rel + "'");
        			}
        		}
        		else {
        			System.out.println("???????????????????????????????? " + id);
        			// effective context on simple entity is equal '.' operator for complex entity
       			// adds effective context
       			addEffectiveContext(id);
     			
        		}
    		return rel;	
    	}

    	protected void complexEntityStartAssembling() throws RecognitionException {
        		// id and name is the same
        		complexEntityNameBuilderDef = ComplexEntityNameBuilder.instance();
        		complexEntityNameBuilderDef.startProgress();
        		String ceId = complexEntityNameBuilderDecl.generateRandomName();
        		boolean participatesInComplexContextBuildingProcess = false;
        		try {
        			if (codeGenerator != null) {
        				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
        				ComplexContextDescriptor contextDescriptor = (ComplexContextDescriptor)contextWalker.peek();
        				if (contextDescriptor != null) {
        					if (contextDescriptor.getUserData() == null) {
        						String[] contextsAsStrings = vwmlContextBuilder.buildContext().asStrings();
        						contextDescriptor.setUserData(EntityDescriptor.build(ceId, contextsAsStrings));
        						participatesInComplexContextBuildingProcess = true;
        					}
    					else {
    						// next complex entity - chance to unwind deffreed effective context
    						unwindEffectiveContext();
    					}
        				}
        				for(String c = contexts.first(); c != null; c = contexts.next()) {
        					codeGenerator.declareComplexEntity(ceId, null, c);
        				}
        			}
        		}
        		catch(Exception e) {
        			rethrowVWMLExceptionAsRecognitionException(e);
        		}
            	// the complex enity (name/id is generated) is pushed to stack (here complex entity is part of expression)
        		EntityWalker.Relation rel = buildRelation(ceId);
        		rel.setData(complexEntityNameBuilderDef);
        		rel.setParticipatesInComplexContextBuildingProcess(participatesInComplexContextBuildingProcess);
        		entityWalker.push(rel);
       		if (logger.isDebugEnabled()) {
       			logger.debug("complex entity '" + rel.getObj() + "' is declared");
       		}
    	}
    	
    	protected EntityWalker.Relation complexEntityStopAssembling() throws RecognitionException {
            	// remove it from stack
        		EntityWalker.Relation rel = (EntityWalker.Relation)entityWalker.pop();
        		// builds complex entity readable name instead of generated
        		complexEntityNameBuilderDef = (ComplexEntityNameBuilder)rel.getData();
        		complexEntityNameBuilderDef.stopProgress();
            	lastProcessedComplexEntityId = complexEntityNameBuilderDef.build();
            	complexEntityNameBuilderDef.clear();    		
        		if (logger.isDebugEnabled()) {
        			logger.debug("processed complex entity '" + rel + "'");
        		}
        		return rel;
    	}
    	
    	protected Object buildIASAssociation(Object id) throws RecognitionException {
        		VWMLContextBuilder.ContextBunch bunch = (VWMLContextBuilder.ContextBunch)entityWalker.getEntityMarkedAsIAS();
        		Object objLinkedId = id;
        		entityWalker.resetFutureEntityAsIAS();
           		// creates 'IAS' association
        		try {
        			// asking for current/active context
        			if (codeGenerator != null) {
        				if (logger.isDebugEnabled()) {
       					logger.debug("Interpreting bunch '" + bunch + "'");
       				}
       				Object lastUniqId = null;
     				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildReducedContextList();
        				for(VWMLContextBuilder.ContextBunchElement cbe = bunch.first(); cbe != null; cbe = bunch.next()) {
        					String firstRelatedContext = contexts.first();
        					for(String c = contexts.first(); c != null; c = contexts.next()) {
        						codeGenerator.interpretObjects(cbe.getId(), objLinkedId, c, c, lastUniqId);
        						lastUniqId = codeGenerator.getLastLinksUniqId();
        						if (logger.isDebugEnabled()) {
       							logger.debug("Interpreting object '" + cbe.getId() + "' -> '" + objLinkedId + "'; on context '" + c + "'; uniq id '" + lastUniqId + "'");
       						}
        					}
        				}
       /*
       				VWMLContextBuilder.Contexts contexts = bunch.getContexts();
        				for(VWMLContextBuilder.ContextBunchElement cbe = bunch.first(); cbe != null; cbe = bunch.next()) {
        					String firstRelatedContext = contexts.first();
        					for(String c = contexts.first(); c != null; c = contexts.next()) {
        					        if (!c.endsWith((String)cbe.getId())) {
        					        	continue;
        					        }
        						codeGenerator.interpretObjects(cbe.getId(), objLinkedId, c, firstRelatedContext);
        						if (logger.isDebugEnabled()) {
       							logger.debug("Interpreting object '" + cbe.getId() + "' -> '" + objLinkedId + "'; on context '" + c + "'");
       						}
        					}
        				}
       */ 				
        			}
        		}
        		catch(Exception e) {
        			rethrowVWMLExceptionAsRecognitionException(e);
        		}	
        		return (codeGenerator != null) ? codeGenerator.getLastLink() : null;
    	}
    	
    	protected Object buildLinkingAssociation(Object linkedObj) throws RecognitionException {
      		Object rel = entityWalker.peek();
      		if (rel == null) {
      			rel = EntityWalker.Relation.build(ComplexEntityNameBuilder.generateRootId(modName), EntityWalker.REL.NONE, null);
      		}
        		if (rel != null) {
        			try {
        				// asking for current/active context
        				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
        				Object linkingObjId = ((EntityWalker.Relation)rel).getObj();
        				if (codeGenerator != null) {
        					String c = contexts.first();
        					codeGenerator.linkObjects(linkingObjId, linkedObj, c, c, null);
        					/*
        					Object uniqId = null;
        					for(String c = contexts.first(); c != null; c = contexts.next()) {
        						codeGenerator.linkObjects(linkingObjId, linkedObj, c, c, uniqId);
        						if (uniqId == null) {
        							uniqId = codeGenerator.getLastLinksUniqId();
        						}
         						if (logger.isDebugEnabled()) {
        							logger.debug("Linked objects '" + linkingObjId + "' -> '" + linkedObj + "'; on context '" + c + "'");
        						}
        					}
        					*/
        				}
        			}
        			catch(Exception e) {
        				rethrowVWMLExceptionAsRecognitionException(e);
        			}
        		}
        		return (codeGenerator != null) ? codeGenerator.getLastLink() : null;
    	}
    	
    	protected EntityWalker.Relation buildRelation(Object eId) throws RecognitionException {
    		Object lastLink = null;
    	     	EntityWalker.REL relType = EntityWalker.REL.NONE;
    	    	// in case if entity was marked as IAS we have to build IAS association
    	    	if (entityWalker.getEntityMarkedAsIAS() != null) {
    	    		relType = EntityWalker.REL.ASSOCIATION;
    	    		lastLink = buildIASAssociation(eId);
    	    	}
    	    	else {
    	    		relType = EntityWalker.REL.LINK;
    	    		// ... otherwise linkage
    	  	
    	  		lastLink = buildLinkingAssociation(eId);
    	  	}
     		return EntityWalker.Relation.build(eId, relType, lastLink);
    	}	
    	
    	protected void deferInclude(String file) {
    		deferredIncludes.add(file);
    	}
    	
    	protected void processDeferredIncludes() throws RecognitionException {
    		for(String file : deferredIncludes) {
    			processInclude(file);
    		}
    	}
    	
    	protected void processInclude(String file) throws RecognitionException {
    		try {
        			if (logger.isInfoEnabled()) {
        				logger.info("including '" + file + "'");
        			}		
    			vwmlModelBuilder.compile(file);
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}
    	} 
    	
    	protected void startConflictDefinitionOnRing(String conflictDefOnRing) throws RecognitionException {
    		try {
    			if (codeGenerator != null) {
    				codeGenerator.startConflictDefinitionOnRing(conflictDefOnRing);
    			}
    			conflictDefinitionOnRingStarted = true;
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}
    	}

    	protected void addConflictDefinitionOnRing(String conflictDefOnRing) throws RecognitionException {
    		try {
    			if (conflictDefinitionOnRingStarted && codeGenerator != null) {
    				codeGenerator.addConflictDefinitionOnRing(conflictDefOnRing);
    			}
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}

    	}

    	protected void endConflictDefinitionOnRing() throws RecognitionException {
    		try {
    			if (codeGenerator != null) {
    				codeGenerator.endConflictDefinitionOnRing();
    			}
    			conflictDefinitionOnRingStarted = false;
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}
    	}
    	
    	protected void rethrowVWMLExceptionAsRecognitionException(Exception e) throws RecognitionException {
    		throw new VWMLCodeGeneratorRecognitionException(e.getMessage());
    	}
    		



    // $ANTLR start "filedef"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:641:1: filedef : ( props )? ( include ( include )* )? ( module )? EOF ;
    public final void filedef() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:5: ( ( props )? ( include ( include )* )? ( module )? EOF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:7: ( props )? ( include ( include )* )? ( module )? EOF
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:7: ( props )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==70) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:7: props
                    {
                    pushFollow(FOLLOW_props_in_filedef437);
                    props();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:14: ( include ( include )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==67) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:15: include ( include )*
                    {
                    pushFollow(FOLLOW_include_in_filedef441);
                    include();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:23: ( include )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==67) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:24: include
                    	    {
                    	    pushFollow(FOLLOW_include_in_filedef444);
                    	    include();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:36: ( module )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==69) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:642:36: module
                    {
                    pushFollow(FOLLOW_module_in_filedef450);
                    module();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,EOF,FOLLOW_EOF_in_filedef453); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                                         	if (moduleInProgress && modProps != null) {
                                         		try {
                                         			// sets special interpretation properties
                                         			// these properties are defined by user and passed by VWML tool to VWML builder 
                                         			modProps.setInterpretationProps(vwmlModelBuilder.getInterpretationProps());
                                         			// actually generates source code
                                         			codeGenerator.generate(modProps);
                                         			// finalizes source generation phase for this module
                                         			codeGenerator.finishModule(modProps);
                                         			// module parsed and finished
                                         			moduleInProgress = false;
                                         			// includes processing now...
                                         			processDeferredIncludes();
                                         		}
                                         		catch(Exception e) {
            		    				logger.error("Caught exception '" + e + "'");
            		    				rethrowVWMLExceptionAsRecognitionException(e);
                                         		}
                              	     	} 
                              	     }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "filedef"



    // $ANTLR start "include"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:665:1: include : include_vwml ;
    public final void include() throws RecognitionException {
        String include_vwml1 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:666:5: ( include_vwml )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:666:7: include_vwml
            {
            pushFollow(FOLLOW_include_vwml_in_include474);
            include_vwml1=include_vwml();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (logger.isInfoEnabled()) {
                				logger.info("deferring include '" + include_vwml1 + "'");
                			}
                			deferInclude(GeneralUtils.trimQuotes(include_vwml1));    			
                               }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "include"



    // $ANTLR start "include_vwml"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:674:1: include_vwml returns [String id] : 'include' STRING_LITERAL ;
    public final String include_vwml() throws RecognitionException {
        String id = null;


        Token STRING_LITERAL2=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:675:5: ( 'include' STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:675:8: 'include' STRING_LITERAL
            {
            match(input,67,FOLLOW_67_in_include_vwml502); if (state.failed) return id;

            STRING_LITERAL2=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_include_vwml504); if (state.failed) return id;

            if ( state.backtracking==0 ) {id = (STRING_LITERAL2!=null?STRING_LITERAL2.getText():null);}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "include_vwml"



    // $ANTLR start "props"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:678:1: props : 'options' '{' optionsList '}' ;
    public final void props() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:679:5: ( 'options' '{' optionsList '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:679:7: 'options' '{' optionsList '}'
            {
            match(input,70,FOLLOW_70_in_props523); if (state.failed) return ;

            match(input,76,FOLLOW_76_in_props525); if (state.failed) return ;

            pushFollow(FOLLOW_optionsList_in_props527);
            optionsList();

            state._fsp--;
            if (state.failed) return ;

            match(input,77,FOLLOW_77_in_props529); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "props"



    // $ANTLR start "optionsList"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:682:1: optionsList : lang ;
    public final void optionsList() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:683:5: ( lang )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:683:7: lang
            {
            pushFollow(FOLLOW_lang_in_optionsList550);
            lang();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "optionsList"



    // $ANTLR start "lang"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:686:1: lang : ( ( 'language' '=' JAVA )=> langJava | otherLanguages );
    public final void lang() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:687:5: ( ( 'language' '=' JAVA )=> langJava | otherLanguages )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==68) && (synpred1_VirtualWorldModelingLanguage())) {
                alt5=1;
            }
            else if ( (LA5_0==77) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:687:7: ( 'language' '=' JAVA )=> langJava
                    {
                    pushFollow(FOLLOW_langJava_in_lang577);
                    langJava();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:688:7: otherLanguages
                    {
                    pushFollow(FOLLOW_otherLanguages_in_lang585);
                    otherLanguages();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "lang"



    // $ANTLR start "otherLanguages"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:691:1: otherLanguages :;
    public final void otherLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:692:5: ()
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:693:5: 
            {
            }

        }
        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "otherLanguages"



    // $ANTLR start "langJava"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:695:1: langJava : 'language' '=' JAVA '{' javaProps '}' ;
    public final void langJava() throws RecognitionException {

               codeGenerator = vwmlModelBuilder.getCodeGenerator(VWMLModelBuilder.SINK_TYPE.JAVA);
               if (vwmlModelBuilder.getProjectProps() != null && vwmlModelBuilder.getProjectProps().getCodeGenerator() == null) {
               		vwmlModelBuilder.getProjectProps().setCodeGenerator(codeGenerator);
               }
               if (logger.isDebugEnabled()) {
               		logger.debug("Code generator '" + codeGenerator + "'");
               }
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:705:5: ( 'language' '=' JAVA '{' javaProps '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:705:7: 'language' '=' JAVA '{' javaProps '}'
            {
            match(input,68,FOLLOW_68_in_langJava628); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_langJava630); if (state.failed) return ;

            match(input,JAVA,FOLLOW_JAVA_in_langJava632); if (state.failed) return ;

            match(input,76,FOLLOW_76_in_langJava634); if (state.failed) return ;

            pushFollow(FOLLOW_javaProps_in_langJava636);
            javaProps();

            state._fsp--;
            if (state.failed) return ;

            match(input,77,FOLLOW_77_in_langJava638); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "langJava"



    // $ANTLR start "javaProps"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:708:1: javaProps : propPackage ( generatedFileLocation )? optionalProps ;
    public final void javaProps() throws RecognitionException {

            	setupProps();
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:712:5: ( propPackage ( generatedFileLocation )? optionalProps )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:712:7: propPackage ( generatedFileLocation )? optionalProps
            {
            pushFollow(FOLLOW_propPackage_in_javaProps664);
            propPackage();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:712:19: ( generatedFileLocation )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==72) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:712:19: generatedFileLocation
                    {
                    pushFollow(FOLLOW_generatedFileLocation_in_javaProps666);
                    generatedFileLocation();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            pushFollow(FOLLOW_optionalProps_in_javaProps669);
            optionalProps();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "javaProps"



    // $ANTLR start "propPackage"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:715:1: propPackage : 'package' '=' packageName ;
    public final void propPackage() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return packageName3 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:716:5: ( 'package' '=' packageName )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:716:7: 'package' '=' packageName
            {
            match(input,71,FOLLOW_71_in_propPackage691); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_propPackage693); if (state.failed) return ;

            pushFollow(FOLLOW_packageName_in_propPackage695);
            packageName3=packageName();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			  if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModulePackage(GeneralUtils.trimQuotes((packageName3!=null?input.toString(packageName3.start,packageName3.stop):null)));
            	    			  }
                			        }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "propPackage"


    public static class packageName_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "packageName"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:723:1: packageName : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.packageName_return packageName() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return retval = new VirtualWorldModelingLanguageParser.packageName_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:724:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:724:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_packageName714); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "packageName"



    // $ANTLR start "generatedFileLocation"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:727:1: generatedFileLocation : 'path' '=' path ;
    public final void generatedFileLocation() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return path4 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:728:5: ( 'path' '=' path )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:728:7: 'path' '=' path
            {
            match(input,72,FOLLOW_72_in_generatedFileLocation731); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_generatedFileLocation733); if (state.failed) return ;

            pushFollow(FOLLOW_path_in_generatedFileLocation735);
            path4=path();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (modProps != null) {
                				((JavaCodeGenerator.JavaModuleStartProps)modProps).setSrcPath(GeneralUtils.trimQuotes((path4!=null?input.toString(path4.start,path4.stop):null)));
                			}
                		      }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "generatedFileLocation"



    // $ANTLR start "optionalProps"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:735:1: optionalProps : ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )? ;
    public final void optionalProps() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:5: ( ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:7: ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )?
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:7: ( author )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==58) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:7: author
                    {
                    pushFollow(FOLLOW_author_in_optionalProps755);
                    author();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:15: ( projname )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==73) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:15: projname
                    {
                    pushFollow(FOLLOW_projname_in_optionalProps758);
                    projname();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:25: ( description )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==64) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:25: description
                    {
                    pushFollow(FOLLOW_description_in_optionalProps761);
                    description();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:38: ( entity_history_size )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==65) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:38: entity_history_size
                    {
                    pushFollow(FOLLOW_entity_history_size_in_optionalProps764);
                    entity_history_size();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:59: ( visualizer )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==75) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:59: visualizer
                    {
                    pushFollow(FOLLOW_visualizer_in_optionalProps767);
                    visualizer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:71: ( beyond_the_fringe )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==59) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:71: beyond_the_fringe
                    {
                    pushFollow(FOLLOW_beyond_the_fringe_in_optionalProps770);
                    beyond_the_fringe();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:90: ( conflictring )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==61) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:736:90: conflictring
                    {
                    pushFollow(FOLLOW_conflictring_in_optionalProps773);
                    conflictring();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "optionalProps"



    // $ANTLR start "author"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:739:1: author : 'author' '=' string ;
    public final void author() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string5 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:740:5: ( 'author' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:740:7: 'author' '=' string
            {
            match(input,58,FOLLOW_58_in_author791); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_author793); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_author795);
            string5=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setAuthor(GeneralUtils.trimQuotes((string5!=null?input.toString(string5.start,string5.stop):null)));
            	    			}
                			  }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "author"



    // $ANTLR start "projname"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:747:1: projname : 'project_name' '=' string ;
    public final void projname() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string6 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:748:5: ( 'project_name' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:748:7: 'project_name' '=' string
            {
            match(input,73,FOLLOW_73_in_projname814); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_projname816); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_projname818);
            string6=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setProjectName(GeneralUtils.trimQuotes((string6!=null?input.toString(string6.start,string6.stop):null)));
            	    			}
                			  }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "projname"



    // $ANTLR start "description"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:755:1: description : 'description' '=' string ;
    public final void description() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string7 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:756:5: ( 'description' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:756:7: 'description' '=' string
            {
            match(input,64,FOLLOW_64_in_description841); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_description843); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_description845);
            string7=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setDescription(GeneralUtils.trimQuotes((string7!=null?input.toString(string7.start,string7.stop):null)));
                				}
                			       }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "description"



    // $ANTLR start "entity_history_size"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:763:1: entity_history_size : 'entity_history_size' '=' string ;
    public final void entity_history_size() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string8 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:764:5: ( 'entity_history_size' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:764:7: 'entity_history_size' '=' string
            {
            match(input,65,FOLLOW_65_in_entity_history_size864); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_entity_history_size866); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_entity_history_size868);
            string8=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setEntityHistorySize(GeneralUtils.trimQuotes((string8!=null?input.toString(string8.start,string8.stop):null)));
                				}
                			       }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entity_history_size"



    // $ANTLR start "visualizer"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:772:1: visualizer : 'visualizer' '{' visualizer_body '}' ;
    public final void visualizer() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:773:5: ( 'visualizer' '{' visualizer_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:773:7: 'visualizer' '{' visualizer_body '}'
            {
            match(input,75,FOLLOW_75_in_visualizer888); if (state.failed) return ;

            match(input,76,FOLLOW_76_in_visualizer890); if (state.failed) return ;

            pushFollow(FOLLOW_visualizer_body_in_visualizer892);
            visualizer_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,77,FOLLOW_77_in_visualizer894); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer"



    // $ANTLR start "visualizer_body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:776:1: visualizer_body : ( visualizer_class visualizer_datapath |);
    public final void visualizer_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:777:5: ( visualizer_class visualizer_datapath |)
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==60) ) {
                alt14=1;
            }
            else if ( (LA14_0==77) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:777:7: visualizer_class visualizer_datapath
                    {
                    pushFollow(FOLLOW_visualizer_class_in_visualizer_body912);
                    visualizer_class();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_visualizer_datapath_in_visualizer_body914);
                    visualizer_datapath();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:779:5: 
                    {
                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer_body"



    // $ANTLR start "visualizer_class"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:781:1: visualizer_class : 'class' '=' string ;
    public final void visualizer_class() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string9 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:782:5: ( 'class' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:782:7: 'class' '=' string
            {
            match(input,60,FOLLOW_60_in_visualizer_class937); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_visualizer_class939); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_class941);
            string9=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitor((AbstractVWMLLinkVisitor)GeneralUtils.instantiateClassThroughStaticMethod(GeneralUtils.trimQuotes((string9!=null?input.toString(string9.start,string9.stop):null)), "instance"));
                				}
                			 }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer_class"



    // $ANTLR start "visualizer_datapath"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:789:1: visualizer_datapath : 'data' '=' string ;
    public final void visualizer_datapath() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string10 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:790:5: ( 'data' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:790:7: 'data' '=' string
            {
            match(input,63,FOLLOW_63_in_visualizer_datapath964); if (state.failed) return ;

            match(input,57,FOLLOW_57_in_visualizer_datapath966); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_datapath968);
            string10=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitorDataPath(GeneralUtils.trimQuotes((string10!=null?input.toString(string10.start,string10.stop):null)));
                				}
                			}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer_datapath"


    public static class path_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "path"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:797:1: path : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.path_return path() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return retval = new VirtualWorldModelingLanguageParser.path_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:798:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:798:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_path991); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "path"



    // $ANTLR start "beyond_the_fringe"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:802:1: beyond_the_fringe : 'beyond' '{' beyond_the_fringe_body '}' ;
    public final void beyond_the_fringe() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:803:5: ( 'beyond' '{' beyond_the_fringe_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:803:7: 'beyond' '{' beyond_the_fringe_body '}'
            {
            match(input,59,FOLLOW_59_in_beyond_the_fringe1009); if (state.failed) return ;

            match(input,76,FOLLOW_76_in_beyond_the_fringe1011); if (state.failed) return ;

            pushFollow(FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe1013);
            beyond_the_fringe_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,77,FOLLOW_77_in_beyond_the_fringe1015); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "beyond_the_fringe"



    // $ANTLR start "beyond_the_fringe_body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:806:1: beyond_the_fringe_body : finges ;
    public final void beyond_the_fringe_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:807:5: ( finges )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:807:8: finges
            {
            pushFollow(FOLLOW_finges_in_beyond_the_fringe_body1037);
            finges();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "beyond_the_fringe_body"



    // $ANTLR start "finges"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:810:1: finges : ( fringe )+ ;
    public final void finges() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:811:5: ( ( fringe )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:811:8: ( fringe )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:811:8: ( fringe )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==66) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:811:9: fringe
            	    {
            	    pushFollow(FOLLOW_fringe_in_finges1056);
            	    fringe();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "finges"



    // $ANTLR start "fringe"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:814:1: fringe : 'fringe' ID 'ias' '(' creatures ')' ;
    public final void fringe() throws RecognitionException {
        Token ID11=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:815:5: ( 'fringe' ID 'ias' '(' creatures ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:815:8: 'fringe' ID 'ias' '(' creatures ')'
            {
            match(input,66,FOLLOW_66_in_fringe1076); if (state.failed) return ;

            ID11=(Token)match(input,ID,FOLLOW_ID_in_fringe1078); if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_fringe1080); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			setActiveFringe(ID11.getText());
                		   }

            match(input,54,FOLLOW_54_in_fringe1103); if (state.failed) return ;

            pushFollow(FOLLOW_creatures_in_fringe1105);
            creatures();

            state._fsp--;
            if (state.failed) return ;

            match(input,55,FOLLOW_55_in_fringe1107); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "fringe"



    // $ANTLR start "creatures"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:821:1: creatures : ( creature )+ ;
    public final void creatures() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:822:5: ( ( creature )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:822:7: ( creature )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:822:7: ( creature )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:822:8: creature
            	    {
            	    pushFollow(FOLLOW_creature_in_creatures1125);
            	    creature();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creatures"



    // $ANTLR start "creature"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:1: creature : ID 'ias' string ;
    public final void creature() throws RecognitionException {
        Token ID12=null;
        VirtualWorldModelingLanguageParser.string_return string13 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:5: ( ID 'ias' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:7: ID 'ias' string
            {
            ID12=(Token)match(input,ID,FOLLOW_ID_in_creature1153); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		addLastDeclaredCreature(ID12.getText());
                	 }

            match(input,IAS,FOLLOW_IAS_in_creature1157); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_creature1159);
            string13=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                	 			addLastDeclaredCreatureProps(GeneralUtils.trimQuotes((string13!=null?input.toString(string13.start,string13.stop):null)));
                	 		}

            }

            if ( state.backtracking==0 ) {
            	if (codeGenerator != null) {
            		try {
            			codeGenerator.declareCreature(getLastDeclaredCreature(), getLastDeclaredCreatureProps(), getActiveFringe());
            		}
            		catch(Exception e) {
            	    		logger.error("Caught exception '" + e + "'");
            	    		rethrowVWMLExceptionAsRecognitionException(e);
            		}
            	}    
                }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creature"



    // $ANTLR start "conflictring"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:844:1: conflictring : 'conflictring' '{' ( conflictdef )* '}' ;
    public final void conflictring() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:5: ( 'conflictring' '{' ( conflictdef )* '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:7: 'conflictring' '{' ( conflictdef )* '}'
            {
            match(input,61,FOLLOW_61_in_conflictring1179); if (state.failed) return ;

            match(input,76,FOLLOW_76_in_conflictring1181); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:26: ( conflictdef )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==STRING_LITERAL) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:26: conflictdef
            	    {
            	    pushFollow(FOLLOW_conflictdef_in_conflictring1183);
            	    conflictdef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            match(input,77,FOLLOW_77_in_conflictring1186); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "conflictring"



    // $ANTLR start "conflictdef"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:848:1: conflictdef : name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')' ;
    public final void conflictdef() throws RecognitionException {
        String name_of_conflict_on_ring14 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:849:5: ( name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:849:7: name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')'
            {
            pushFollow(FOLLOW_name_of_conflict_on_ring_in_conflictdef1204);
            name_of_conflict_on_ring14=name_of_conflict_on_ring();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { startConflictDefinitionOnRing(GeneralUtils.trimQuotes(name_of_conflict_on_ring14)); }

            match(input,62,FOLLOW_62_in_conflictdef1208); if (state.failed) return ;

            match(input,54,FOLLOW_54_in_conflictdef1210); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:849:138: ( name_of_related_conflict_on_ring )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==STRING_LITERAL) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:849:138: name_of_related_conflict_on_ring
            	    {
            	    pushFollow(FOLLOW_name_of_related_conflict_on_ring_in_conflictdef1212);
            	    name_of_related_conflict_on_ring();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);


            match(input,55,FOLLOW_55_in_conflictdef1215); if (state.failed) return ;

            if ( state.backtracking==0 ) { endConflictDefinitionOnRing(); }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "conflictdef"



    // $ANTLR start "name_of_conflict_on_ring"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:852:1: name_of_conflict_on_ring returns [String id] : string ;
    public final String name_of_conflict_on_ring() throws RecognitionException {
        String id = null;


        VirtualWorldModelingLanguageParser.string_return string15 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:853:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:853:7: string
            {
            pushFollow(FOLLOW_string_in_name_of_conflict_on_ring1242);
            string15=string();

            state._fsp--;
            if (state.failed) return id;

            if ( state.backtracking==0 ) { id = (string15!=null?input.toString(string15.start,string15.stop):null); }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "name_of_conflict_on_ring"



    // $ANTLR start "name_of_related_conflict_on_ring"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:856:1: name_of_related_conflict_on_ring : string ;
    public final void name_of_related_conflict_on_ring() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string16 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:857:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:857:7: string
            {
            pushFollow(FOLLOW_string_in_name_of_related_conflict_on_ring1263);
            string16=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { addConflictDefinitionOnRing(GeneralUtils.trimQuotes((string16!=null?input.toString(string16.start,string16.stop):null))); }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "name_of_related_conflict_on_ring"



    // $ANTLR start "module"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:860:1: module : 'module' ID body ;
    public final void module() throws RecognitionException {
        Token ID17=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:861:5: ( 'module' ID body )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:861:7: 'module' ID body
            {
            match(input,69,FOLLOW_69_in_module1282); if (state.failed) return ;

            ID17=(Token)match(input,ID,FOLLOW_ID_in_module1284); if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                			modName = ID17.getText();
                			logger.info("Compiling module '" + modName + "'");
                			setupProps();
                			// normalizes module's properties; if some properties were not set they are filled by project's properties
                			// ... so it is way to override them 
                			modProps = vwmlModelBuilder.normalizeProps(modProps);
                			// associates module's name with module info structure (will be used on last dource generation phase, especially during unit-test generation)
                			vwmlModelBuilder.addModuleInfo(modName, VWMLModuleInfo.build(modProps, null));
                			if (modProps != null) {
                				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModuleName(modName);
            	    			try {
            	    				if (codeGenerator == null) {
            	    					codeGenerator = modProps.getCodeGenerator();
            	    				}
            	    				if (codeGenerator == null) {
            	    					throw new Exception("Code generator can't be 'null'");
            	    				}
            	    				codeGenerator.startModule(modProps);
            	    				moduleInProgress = true;
            	    			}
            	    			catch(Exception e) {
            	    				logger.error("Caught exception '" + e + "'");
            	    				rethrowVWMLExceptionAsRecognitionException(e);
            	    			}
                			}
                			// starts module's definition
                              }

            pushFollow(FOLLOW_body_in_module1288);
            body();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "module"



    // $ANTLR start "body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:891:1: body : '{' ( expression ( expression )* )? '}' ;
    public final void body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:892:4: ( '{' ( expression ( expression )* )? '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:892:6: '{' ( expression ( expression )* )? '}'
            {
            match(input,76,FOLLOW_76_in_body1308); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:892:10: ( expression ( expression )* )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ID||LA20_0==LIFETERM||LA20_0==54||LA20_0==56||LA20_0==74) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:892:11: expression ( expression )*
                    {
                    pushFollow(FOLLOW_expression_in_body1311);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:892:22: ( expression )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==ID||LA19_0==LIFETERM||LA19_0==54||LA19_0==56||LA19_0==74) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:892:23: expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_body1314);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,77,FOLLOW_77_in_body1320); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "body"



    // $ANTLR start "expression"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:896:1: expression : ( ( bunch_of_entity_decls IAS )=> entity_def | check_term_def );
    public final void expression() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:897:5: ( ( bunch_of_entity_decls IAS )=> entity_def | check_term_def )
            int alt21=2;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA21_1 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt21=1;
                }
                else if ( (true) ) {
                    alt21=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;

                }
                }
                break;
            case 54:
                {
                int LA21_2 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt21=1;
                }
                else if ( (true) ) {
                    alt21=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 2, input);

                    throw nvae;

                }
                }
                break;
            case LIFETERM:
            case 56:
            case 74:
                {
                alt21=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:897:7: ( bunch_of_entity_decls IAS )=> entity_def
                    {
                    pushFollow(FOLLOW_entity_def_in_expression1346);
                    entity_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:898:7: check_term_def
                    {
                    pushFollow(FOLLOW_check_term_def_in_expression1354);
                    check_term_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "expression"



    // $ANTLR start "entity_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:901:1: entity_def : bunch_of_entity_decls IAS ( term )* SEMICOLON ;
    public final void entity_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:902:5: ( bunch_of_entity_decls IAS ( term )* SEMICOLON )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:902:7: bunch_of_entity_decls IAS ( term )* SEMICOLON
            {
            pushFollow(FOLLOW_bunch_of_entity_decls_in_entity_def1371);
            bunch_of_entity_decls();

            state._fsp--;
            if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_entity_def1373); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			// adds entity id to context stack
                			declareAbsoluteContextByIASRelation();
                		      }

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:905:15: ( term )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==ID||LA22_0==LIFETERM||LA22_0==54||LA22_0==56||LA22_0==74) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:905:16: term
            	    {
            	    pushFollow(FOLLOW_term_in_entity_def1378);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entity_def1382); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		      	// removes top entity from stack
                		      	handleProcessedAbsoluteContextbyIASRelation();
                		      }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entity_def"



    // $ANTLR start "check_term_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:913:1: check_term_def : ( ( source_lifetrerm )? LIFETERM '=' lifeterm_def | term_def );
    public final void check_term_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:914:5: ( ( source_lifetrerm )? LIFETERM '=' lifeterm_def | term_def )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==LIFETERM||LA24_0==74) ) {
                alt24=1;
            }
            else if ( (LA24_0==ID||LA24_0==54||LA24_0==56) ) {
                alt24=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:914:7: ( source_lifetrerm )? LIFETERM '=' lifeterm_def
                    {
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:914:7: ( source_lifetrerm )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==74) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:914:7: source_lifetrerm
                            {
                            pushFollow(FOLLOW_source_lifetrerm_in_check_term_def1426);
                            source_lifetrerm();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,LIFETERM,FOLLOW_LIFETERM_in_check_term_def1429); if (state.failed) return ;

                    match(input,57,FOLLOW_57_in_check_term_def1431); if (state.failed) return ;

                    pushFollow(FOLLOW_lifeterm_def_in_check_term_def1433);
                    lifeterm_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:915:7: term_def
                    {
                    pushFollow(FOLLOW_term_def_in_check_term_def1441);
                    term_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "check_term_def"



    // $ANTLR start "source_lifetrerm"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:918:1: source_lifetrerm : 'source' ;
    public final void source_lifetrerm() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:919:5: ( 'source' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:919:7: 'source'
            {
            match(input,74,FOLLOW_74_in_source_lifetrerm1458); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (logger.isDebugEnabled()) {
                				logger.debug("source lifeterm indicator detected");
                			}
                			sourceLifeTermDetectedFlag = true;
                	       }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "source_lifetrerm"



    // $ANTLR start "lifeterm_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:927:1: lifeterm_def : term_def ;
    public final void lifeterm_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:928:5: ( term_def )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:928:8: term_def
            {
            pushFollow(FOLLOW_term_def_in_lifeterm_def1478);
            term_def();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (logger.isInfoEnabled()) {
                				logger.info("Lifeterm '" + lastProcessedEntity + "' found");
                			}
                			if (codeGenerator != null) {
              	       			try {
            					codeGenerator.markEntityAsLifeTerm(lastProcessedEntity, sourceLifeTermDetectedFlag);
            					if (logger.isDebugEnabled()) {
            						logger.debug("entity '" + lastProcessedEntity + "' marked as lifeterm; is source '" + sourceLifeTermDetectedFlag + "'");
            					}
            					sourceLifeTermDetectedFlag = false;
            				}
            				catch(Exception e) {
            					rethrowVWMLExceptionAsRecognitionException(e);
            				}
            	       		} 
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "lifeterm_def"



    // $ANTLR start "term_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:947:1: term_def : entity ( oplist )* ;
    public final void term_def() throws RecognitionException {
        EntityWalker.Relation entity18 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:948:5: ( entity ( oplist )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:948:7: entity ( oplist )*
            {
            pushFollow(FOLLOW_entity_in_term_def1497);
            entity18=entity();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		lastProcessedEntity = entity18;
                		lastProcessedEntityAsTerm = false;
                		if (lastProcessedEntity != null && logger.isDebugEnabled()) {
                			logger.debug(">> '" + lastProcessedEntity.getObj() + "' <<");
                		}
                	     }

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:954:13: ( oplist )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0 >= OPACTIVATECTX && LA25_0 <= OPSUBSTRUCT)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:954:14: oplist
            	    {
            	    pushFollow(FOLLOW_oplist_in_term_def1502);
            	    oplist();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            if ( state.backtracking==0 ) {  	     
              	       if (lastProcessedEntityAsTerm && codeGenerator != null) {
              	       		try {
              	       			VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
            				if (logger.isDebugEnabled()) {
            					logger.debug("entity '" + lastProcessedEntity + "' checking term prop on contexts '" + contexts + "'");
            				}
            				codeGenerator.markEntityAsTerm(lastProcessedEntity, contexts.asStrings());
            				if (logger.isDebugEnabled()) {
            					logger.debug("entity '" + lastProcessedEntity + "' marked as term on contexts '" + contexts + "'");
            				}
            			}
            			catch(Exception e) {
            				rethrowVWMLExceptionAsRecognitionException(e);
            			}
            	       } 
            	     }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "term_def"



    // $ANTLR start "entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:974:1: entity_decl : ( simple_entity_decl | complex_entity_decl );
    public final void entity_decl() throws RecognitionException {
        String simple_entity_decl19 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:975:5: ( simple_entity_decl | complex_entity_decl )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==ID) ) {
                alt26=1;
            }
            else if ( (LA26_0==54) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:975:7: simple_entity_decl
                    {
                    pushFollow(FOLLOW_simple_entity_decl_in_entity_decl1532);
                    simple_entity_decl19=simple_entity_decl();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        				if (!complexEntityNameBuilderDecl.isInProgress()) {
                        					lastProcessedContextBunch.add(ContextBunchElement.build(simple_entity_decl19));
                        					if (logger.isDebugEnabled()) {
                        						logger.debug("+++++++++++++++++++++++ " + simple_entity_decl19);
                        					}
                        				}
                        			  }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:983:7: complex_entity_decl
                    {
                    pushFollow(FOLLOW_complex_entity_decl_in_entity_decl1543);
                    complex_entity_decl();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        				if (complexEntityNameBuilderDecl.isRootEntityFinishedProgress()) {
                      					Object id = complexEntityDeclarationPhase3();
                        					lastProcessedContextBunch.add(ContextBunchElement.build(id));
                         					if (logger.isDebugEnabled()) {
                        						logger.debug("+++++++++++++++++++++++ " + id);
                        					}
                        				}
                        			  }

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entity_decl"



    // $ANTLR start "bunch_of_entity_decls"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:995:1: bunch_of_entity_decls : entity_decl ( COMMA entity_decl )* ;
    public final void bunch_of_entity_decls() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1003:5: ( entity_decl ( COMMA entity_decl )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1003:8: entity_decl ( COMMA entity_decl )*
            {
            if ( state.backtracking==0 ) {    	
                		lastProcessedContextBunch = VWMLContextBuilder.ContextBunch.instance();
                		if (logger.isDebugEnabled()) {
                			logger.debug("Created bunch");
                		}
            	}

            pushFollow(FOLLOW_entity_decl_in_bunch_of_entity_decls1575);
            entity_decl();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1008:16: ( COMMA entity_decl )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==COMMA) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1008:17: COMMA entity_decl
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_bunch_of_entity_decls1578); if (state.failed) return ;

            	    pushFollow(FOLLOW_entity_decl_in_bunch_of_entity_decls1580);
            	    entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {
                	VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
                    vwmlContextBuilder.push(lastProcessedContextBunch);
                    if (logger.isDebugEnabled()) {
                    	logger.debug("Pushed '" + lastProcessedContextBunch + "'; parent contexts '" + contexts + "'");
                    }
                }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "bunch_of_entity_decls"



    // $ANTLR start "simple_entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1012:1: simple_entity_decl returns [String id] : ID ;
    public final String simple_entity_decl() throws RecognitionException {
        String id = null;


        Token ID20=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1013:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1013:7: ID
            {
            ID20=(Token)match(input,ID,FOLLOW_ID_in_simple_entity_decl1609); if (state.failed) return id;

            if ( state.backtracking==0 ) { id = simpleEntityDeclaration(ID20.getText()); }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "simple_entity_decl"



    // $ANTLR start "complex_entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1016:1: complex_entity_decl : '(' ( entity_decl )* ')' ;
    public final void complex_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1017:5: ( '(' ( entity_decl )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1017:7: '(' ( entity_decl )* ')'
            {
            match(input,54,FOLLOW_54_in_complex_entity_decl1632); if (state.failed) return ;

            if ( state.backtracking==0 ) {complexEntityDeclarationPhase1();}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1017:47: ( entity_decl )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==ID||LA28_0==54) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1017:48: entity_decl
            	    {
            	    pushFollow(FOLLOW_entity_decl_in_complex_entity_decl1637);
            	    entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            if ( state.backtracking==0 ) {complexEntityDeclarationPhase2();}

            match(input,55,FOLLOW_55_in_complex_entity_decl1643); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "complex_entity_decl"



    // $ANTLR start "term"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1020:1: term : expression ;
    public final void term() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1021:5: ( expression )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1021:7: expression
            {
            pushFollow(FOLLOW_expression_in_term1660);
            expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "term"



    // $ANTLR start "entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1024:1: entity returns [EntityWalker.Relation rel] : ( simple_entity | complex_entity | '.' );
    public final EntityWalker.Relation entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation simple_entity21 =null;

        EntityWalker.Relation complex_entity22 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1025:5: ( simple_entity | complex_entity | '.' )
            int alt29=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt29=1;
                }
                break;
            case 54:
                {
                alt29=2;
                }
                break;
            case 56:
                {
                alt29=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return rel;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }

            switch (alt29) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1025:7: simple_entity
                    {
                    pushFollow(FOLLOW_simple_entity_in_entity1683);
                    simple_entity21=simple_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) { 
                        				rel = simple_entity21;
                        			    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1029:7: complex_entity
                    {
                    pushFollow(FOLLOW_complex_entity_in_entity1702);
                    complex_entity22=complex_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) { 
                        				rel = complex_entity22;
                        			    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1032:7: '.'
                    {
                    match(input,56,FOLLOW_56_in_entity1719); if (state.failed) return rel;

                    if ( state.backtracking==0 ) {
                                                	processComplexContext(lastProcessedEntity);
                                                }

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "entity"



    // $ANTLR start "simple_entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1038:1: simple_entity returns [EntityWalker.Relation rel] : ID ;
    public final EntityWalker.Relation simple_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        Token ID23=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1039:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1039:7: ID
            {
            ID23=(Token)match(input,ID,FOLLOW_ID_in_simple_entity1761); if (state.failed) return rel;

            if ( state.backtracking==0 ) {
                		rel = simpleEntityAssembling((ID23!=null?ID23.getText():null));
                     }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "simple_entity"



    // $ANTLR start "complex_entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1044:1: complex_entity returns [EntityWalker.Relation rel] : '(' ( term )* ')' ;
    public final EntityWalker.Relation complex_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;



            	complexEntityStartAssembling();
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1051:5: ( '(' ( term )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1051:7: '(' ( term )* ')'
            {
            match(input,54,FOLLOW_54_in_complex_entity1802); if (state.failed) return rel;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1051:11: ( term )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==ID||LA30_0==LIFETERM||LA30_0==54||LA30_0==56||LA30_0==74) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1051:12: term
            	    {
            	    pushFollow(FOLLOW_term_in_complex_entity1805);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return rel;

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            match(input,55,FOLLOW_55_in_complex_entity1809); if (state.failed) return rel;

            }

            if ( state.backtracking==0 ) {
                    rel = complexEntityStopAssembling();
                }
        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "complex_entity"



    // $ANTLR start "oplist"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1063:1: oplist : opclist ;
    public final void oplist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return opclist24 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1065:5: ( opclist )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1065:7: opclist
            {
            pushFollow(FOLLOW_opclist_in_oplist1901);
            opclist24=opclist();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (lastProcessedEntity != null && codeGenerator != null) { 
                				lastProcessedEntityAsTerm = true;
                				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
                				String c = contexts.first();
                				codeGenerator.associateOperation(lastProcessedEntity, (opclist24!=null?input.toString(opclist24.start,opclist24.stop):null), c);
                			} 
                		    }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "oplist"


    public static class opclist_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "opclist"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1075:1: opclist : ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM | OPACTIVATECTX | OPACTIVATEONFRINGE | OPRELAX | OPSTARTCONFLICTGROUP | OPENDCONFLICTGROUP | OPBREAKPOINT | OPAPPLYTOCONTEXT | OPCLONE | OPBORN | OPPROJECTION | OPFOREACH | OPDYNCONTEXT | OPSIZE | OPINTERRUPT | OPCALLP | OPGET | OPFIND );
    public final VirtualWorldModelingLanguageParser.opclist_return opclist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return retval = new VirtualWorldModelingLanguageParser.opclist_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1076:5: ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM | OPACTIVATECTX | OPACTIVATEONFRINGE | OPRELAX | OPSTARTCONFLICTGROUP | OPENDCONFLICTGROUP | OPBREAKPOINT | OPAPPLYTOCONTEXT | OPCLONE | OPBORN | OPPROJECTION | OPFOREACH | OPDYNCONTEXT | OPSIZE | OPINTERRUPT | OPCALLP | OPGET | OPFIND )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( (input.LA(1) >= OPACTIVATECTX && input.LA(1) <= OPSUBSTRUCT) ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "opclist"



    // $ANTLR start "termLanguages"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1112:1: termLanguages : ( JAVA | C | CPP | OBJECTIVEC );
    public final void termLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1113:5: ( JAVA | C | CPP | OBJECTIVEC )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( input.LA(1)==C||input.LA(1)==CPP||input.LA(1)==JAVA||input.LA(1)==OBJECTIVEC ) {
                input.consume();
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "termLanguages"


    public static class string_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "string"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1119:1: string : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.string_return string() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return retval = new VirtualWorldModelingLanguageParser.string_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1120:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1120:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string2248); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "string"

    // $ANTLR start synpred1_VirtualWorldModelingLanguage
    public final void synpred1_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:687:7: ( 'language' '=' JAVA )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:687:8: 'language' '=' JAVA
        {
        match(input,68,FOLLOW_68_in_synpred1_VirtualWorldModelingLanguage568); if (state.failed) return ;

        match(input,57,FOLLOW_57_in_synpred1_VirtualWorldModelingLanguage570); if (state.failed) return ;

        match(input,JAVA,FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage572); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_VirtualWorldModelingLanguage

    // $ANTLR start synpred2_VirtualWorldModelingLanguage
    public final void synpred2_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:897:7: ( bunch_of_entity_decls IAS )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:897:8: bunch_of_entity_decls IAS
        {
        pushFollow(FOLLOW_bunch_of_entity_decls_in_synpred2_VirtualWorldModelingLanguage1339);
        bunch_of_entity_decls();

        state._fsp--;
        if (state.failed) return ;

        match(input,IAS,FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1341); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_VirtualWorldModelingLanguage

    // Delegated rules

    public final boolean synpred1_VirtualWorldModelingLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_VirtualWorldModelingLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_VirtualWorldModelingLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_VirtualWorldModelingLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_props_in_filedef437 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000028L});
    public static final BitSet FOLLOW_include_in_filedef441 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000028L});
    public static final BitSet FOLLOW_include_in_filedef444 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000028L});
    public static final BitSet FOLLOW_module_in_filedef450 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_filedef453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_include_vwml_in_include474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_include_vwml502 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_include_vwml504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_props523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_props525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_optionsList_in_props527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_props529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lang_in_optionsList550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_langJava_in_lang577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_otherLanguages_in_lang585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_langJava628 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_langJava630 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_langJava632 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_langJava634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_javaProps_in_langJava636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_langJava638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propPackage_in_javaProps664 = new BitSet(new long[]{0x2C00000000000000L,0x0000000000000B03L});
    public static final BitSet FOLLOW_generatedFileLocation_in_javaProps666 = new BitSet(new long[]{0x2C00000000000000L,0x0000000000000A03L});
    public static final BitSet FOLLOW_optionalProps_in_javaProps669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_propPackage691 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_propPackage693 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_packageName_in_propPackage695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_packageName714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_generatedFileLocation731 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_generatedFileLocation733 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_path_in_generatedFileLocation735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_author_in_optionalProps755 = new BitSet(new long[]{0x2800000000000002L,0x0000000000000A03L});
    public static final BitSet FOLLOW_projname_in_optionalProps758 = new BitSet(new long[]{0x2800000000000002L,0x0000000000000803L});
    public static final BitSet FOLLOW_description_in_optionalProps761 = new BitSet(new long[]{0x2800000000000002L,0x0000000000000802L});
    public static final BitSet FOLLOW_entity_history_size_in_optionalProps764 = new BitSet(new long[]{0x2800000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_visualizer_in_optionalProps767 = new BitSet(new long[]{0x2800000000000002L});
    public static final BitSet FOLLOW_beyond_the_fringe_in_optionalProps770 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_conflictring_in_optionalProps773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_author791 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_author793 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_string_in_author795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_projname814 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_projname816 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_string_in_projname818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_description841 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_description843 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_string_in_description845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_entity_history_size864 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_entity_history_size866 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_string_in_entity_history_size868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_visualizer888 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_visualizer890 = new BitSet(new long[]{0x1000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_visualizer_body_in_visualizer892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_visualizer894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_visualizer_class_in_visualizer_body912 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_visualizer_datapath_in_visualizer_body914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_visualizer_class937 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_visualizer_class939 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_class941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_visualizer_datapath964 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_visualizer_datapath966 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_string_in_visualizer_datapath968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_path991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_beyond_the_fringe1009 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_beyond_the_fringe1011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe1013 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_beyond_the_fringe1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finges_in_beyond_the_fringe_body1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fringe_in_finges1056 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_fringe1076 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_fringe1078 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_fringe1080 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_fringe1103 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_creatures_in_fringe1105 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_fringe1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creature_in_creatures1125 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_ID_in_creature1153 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_creature1157 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_string_in_creature1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_conflictring1179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_conflictring1181 = new BitSet(new long[]{0x0010000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_conflictdef_in_conflictring1183 = new BitSet(new long[]{0x0010000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_conflictring1186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_of_conflict_on_ring_in_conflictdef1204 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_conflictdef1208 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_conflictdef1210 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_name_of_related_conflict_on_ring_in_conflictdef1212 = new BitSet(new long[]{0x0090000000000000L});
    public static final BitSet FOLLOW_55_in_conflictdef1215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_name_of_conflict_on_ring1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_name_of_related_conflict_on_ring1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_module1282 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_module1284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_body_in_module1288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_body1308 = new BitSet(new long[]{0x0140000000002400L,0x0000000000002400L});
    public static final BitSet FOLLOW_expression_in_body1311 = new BitSet(new long[]{0x0140000000002400L,0x0000000000002400L});
    public static final BitSet FOLLOW_expression_in_body1314 = new BitSet(new long[]{0x0140000000002400L,0x0000000000002400L});
    public static final BitSet FOLLOW_77_in_body1320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_def_in_expression1346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_term_def_in_expression1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bunch_of_entity_decls_in_entity_def1371 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_entity_def1373 = new BitSet(new long[]{0x0148000000002400L,0x0000000000000400L});
    public static final BitSet FOLLOW_term_in_entity_def1378 = new BitSet(new long[]{0x0148000000002400L,0x0000000000000400L});
    public static final BitSet FOLLOW_SEMICOLON_in_entity_def1382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_source_lifetrerm_in_check_term_def1426 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LIFETERM_in_check_term_def1429 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_check_term_def1431 = new BitSet(new long[]{0x0140000000000400L});
    public static final BitSet FOLLOW_lifeterm_def_in_check_term_def1433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_check_term_def1441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_source_lifetrerm1458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_lifeterm_def1478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_term_def1497 = new BitSet(new long[]{0x0007FFFFFFFE0002L});
    public static final BitSet FOLLOW_oplist_in_term_def1502 = new BitSet(new long[]{0x0007FFFFFFFE0002L});
    public static final BitSet FOLLOW_simple_entity_decl_in_entity_decl1532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_decl_in_entity_decl1543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_bunch_of_entity_decls1575 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_bunch_of_entity_decls1578 = new BitSet(new long[]{0x0040000000000400L});
    public static final BitSet FOLLOW_entity_decl_in_bunch_of_entity_decls1580 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ID_in_simple_entity_decl1609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_complex_entity_decl1632 = new BitSet(new long[]{0x00C0000000000400L});
    public static final BitSet FOLLOW_entity_decl_in_complex_entity_decl1637 = new BitSet(new long[]{0x00C0000000000400L});
    public static final BitSet FOLLOW_55_in_complex_entity_decl1643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_term1660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_entity_in_entity1683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_in_entity1702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_entity1719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity1761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_complex_entity1802 = new BitSet(new long[]{0x01C0000000002400L,0x0000000000000400L});
    public static final BitSet FOLLOW_term_in_complex_entity1805 = new BitSet(new long[]{0x01C0000000002400L,0x0000000000000400L});
    public static final BitSet FOLLOW_55_in_complex_entity1809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opclist_in_oplist1901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string2248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_synpred1_VirtualWorldModelingLanguage568 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_synpred1_VirtualWorldModelingLanguage570 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bunch_of_entity_decls_in_synpred2_VirtualWorldModelingLanguage1339 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1341 = new BitSet(new long[]{0x0000000000000002L});

}