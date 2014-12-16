// $ANTLR 3.4 C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2014-12-16 17:57:33

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

// preprocessor
import com.vw.lang.grammar.preprocessor.VWMLPreprocessor;
import com.vw.lang.grammar.preprocessor.VWMLPreprocessor.VWMLPreprocessorIfDirective;

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "C", "COMMA", "COMMENT", "CPP", "DQUOTE", "IAS", "ID", "JAVA", "LETTER", "LIFETERM", "LINE_COMMENT", "NATIVE_CODE", "OBJECTIVEC", "OPACTIVATE", "OPACTIVATECTX", "OPACTIVATEONFRINGE", "OPAPPLYTOCONTEXT", "OPBEGIN", "OPBORN", "OPBREAKPOINT", "OPCALLP", "OPCARTESIAN", "OPCLONE", "OPCOPY", "OPCREATEEXPR", "OPDYNCONTEXT", "OPENDCONFLICTGROUP", "OPEQ", "OPEXECUTE", "OPEXISTSI", "OPEXISTSI_S", "OPFIND", "OPFINISHINTERCEPTION", "OPFINISHINTERCEPTION_S", "OPFIRST", "OPFOREACH", "OPGATE", "OPGET", "OPIDENT", "OPIN", "OPINCL", "OPINTERPRET", "OPINTERRUPT", "OPINTERSECT", "OPJOIN", "OPLAST", "OPLTT", "OPPROJECTION", "OPRANDOM", "OPRECALL", "OPRELAX", "OPRELEASE", "OPREPEAT", "OPREST", "OPSIZE", "OPSQU", "OPSTARTCONFLICTGROUP", "OPSTARTINTERCEPTION", "OPSTARTINTERCEPTION_S", "OPSUBSTRUCT", "P_DEBUG", "P_ELSE", "P_ENDIF", "P_IF", "P_OP_AND", "P_OP_B", "P_OP_E", "P_OP_L", "P_OP_OR", "SEMICOLON", "STRING_LITERAL", "WS", "'('", "')'", "'.'", "'author'", "'beyond'", "'class'", "'conflictring'", "'conflicts'", "'contexts'", "'data'", "'description'", "'entities'", "'entity_history_size'", "'external'", "'fringe'", "'include'", "'language'", "'module'", "'options'", "'package'", "'path'", "'project_name'", "'source'", "'visualizer'", "'{'", "'}'"
    };

    public static final int EOF=-1;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__99=99;
    public static final int T__100=100;
    public static final int T__101=101;
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
    public static final int OPACTIVATE=17;
    public static final int OPACTIVATECTX=18;
    public static final int OPACTIVATEONFRINGE=19;
    public static final int OPAPPLYTOCONTEXT=20;
    public static final int OPBEGIN=21;
    public static final int OPBORN=22;
    public static final int OPBREAKPOINT=23;
    public static final int OPCALLP=24;
    public static final int OPCARTESIAN=25;
    public static final int OPCLONE=26;
    public static final int OPCOPY=27;
    public static final int OPCREATEEXPR=28;
    public static final int OPDYNCONTEXT=29;
    public static final int OPENDCONFLICTGROUP=30;
    public static final int OPEQ=31;
    public static final int OPEXECUTE=32;
    public static final int OPEXISTSI=33;
    public static final int OPEXISTSI_S=34;
    public static final int OPFIND=35;
    public static final int OPFINISHINTERCEPTION=36;
    public static final int OPFINISHINTERCEPTION_S=37;
    public static final int OPFIRST=38;
    public static final int OPFOREACH=39;
    public static final int OPGATE=40;
    public static final int OPGET=41;
    public static final int OPIDENT=42;
    public static final int OPIN=43;
    public static final int OPINCL=44;
    public static final int OPINTERPRET=45;
    public static final int OPINTERRUPT=46;
    public static final int OPINTERSECT=47;
    public static final int OPJOIN=48;
    public static final int OPLAST=49;
    public static final int OPLTT=50;
    public static final int OPPROJECTION=51;
    public static final int OPRANDOM=52;
    public static final int OPRECALL=53;
    public static final int OPRELAX=54;
    public static final int OPRELEASE=55;
    public static final int OPREPEAT=56;
    public static final int OPREST=57;
    public static final int OPSIZE=58;
    public static final int OPSQU=59;
    public static final int OPSTARTCONFLICTGROUP=60;
    public static final int OPSTARTINTERCEPTION=61;
    public static final int OPSTARTINTERCEPTION_S=62;
    public static final int OPSUBSTRUCT=63;
    public static final int P_DEBUG=64;
    public static final int P_ELSE=65;
    public static final int P_ENDIF=66;
    public static final int P_IF=67;
    public static final int P_OP_AND=68;
    public static final int P_OP_B=69;
    public static final int P_OP_E=70;
    public static final int P_OP_L=71;
    public static final int P_OP_OR=72;
    public static final int SEMICOLON=73;
    public static final int STRING_LITERAL=74;
    public static final int WS=75;

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
     	private List<String> externalContexts = new ArrayList<String>();
     	private List<String> externalEntities = new ArrayList<String>();
     	private VWMLPreprocessor preprocessor = VWMLPreprocessor.instance();
     	
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
    	
    	protected void externalContext(String externalContext) {
    		externalContexts.add(externalContext);
    	}

    	protected void externalEntity(String externalEntity) {
    		externalEntities.add(externalEntity);
    	}
    	
    	protected void processExternals() throws RecognitionException {
    		for(String context : externalContexts) {
    			if (logger.isInfoEnabled()) {
    				logger.info("processing external context '" + context + "'");
    			}
    			if (codeGenerator != null) {
    				codeGenerator.declareContext(context);
    			}
    		}
    	}
    	
    	protected void rethrowVWMLExceptionAsRecognitionException(Exception e) throws RecognitionException {
    		throw new VWMLCodeGeneratorRecognitionException(e.getMessage());
    	}
    	
    	// DIRECTIVES	
    	protected boolean skipOff() throws RecognitionException {
    		try {
    			return !preprocessor.getResultOfProcessingDirectiveIf();
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}
    		return false;
    	}
    	



    // $ANTLR start "filedef"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:703:1: filedef : ( props )? ( include ( include )* )? ( external )? ( module )? EOF ;
    public final void filedef() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:5: ( ( props )? ( include ( include )* )? ( external )? ( module )? EOF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:7: ( props )? ( include ( include )* )? ( external )? ( module )? EOF
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:7: ( props )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==94) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:7: props
                    {
                    pushFollow(FOLLOW_props_in_filedef673);
                    props();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:14: ( include ( include )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==91) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:15: include ( include )*
                    {
                    pushFollow(FOLLOW_include_in_filedef677);
                    include();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:23: ( include )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==91) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:24: include
                    	    {
                    	    pushFollow(FOLLOW_include_in_filedef680);
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


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:36: ( external )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==89) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:36: external
                    {
                    pushFollow(FOLLOW_external_in_filedef686);
                    external();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:46: ( module )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==93) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:704:46: module
                    {
                    pushFollow(FOLLOW_module_in_filedef689);
                    module();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input,EOF,FOLLOW_EOF_in_filedef692); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                                         	if (moduleInProgress && modProps != null) {
                                         		try {
                                         			// sets special interpretation properties
                                         			// these properties are defined by user and passed by VWML tool to VWML builder 
                                         			modProps.setInterpretationProps(vwmlModelBuilder.getInterpretationProps());
                                         			// process externals
                                         			processExternals();
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "filedef"



    // $ANTLR start "external"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:729:1: external : 'external' '{' externalBody '}' ;
    public final void external() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:730:5: ( 'external' '{' externalBody '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:730:7: 'external' '{' externalBody '}'
            {
            match(input,89,FOLLOW_89_in_external713); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_external715); if (state.failed) return ;

            pushFollow(FOLLOW_externalBody_in_external717);
            externalBody();

            state._fsp--;
            if (state.failed) return ;

            match(input,101,FOLLOW_101_in_external719); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "external"



    // $ANTLR start "externalBody"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:733:1: externalBody : ( externalContexts )? ( externalEntities )? ;
    public final void externalBody() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:734:5: ( ( externalContexts )? ( externalEntities )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:734:7: ( externalContexts )? ( externalEntities )?
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:734:7: ( externalContexts )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==84) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:734:7: externalContexts
                    {
                    pushFollow(FOLLOW_externalContexts_in_externalBody736);
                    externalContexts();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:734:25: ( externalEntities )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==87) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:734:25: externalEntities
                    {
                    pushFollow(FOLLOW_externalEntities_in_externalBody739);
                    externalEntities();

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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "externalBody"



    // $ANTLR start "externalContexts"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:737:1: externalContexts : 'contexts' '{' ( externalContext )* '}' ;
    public final void externalContexts() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:738:5: ( 'contexts' '{' ( externalContext )* '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:738:7: 'contexts' '{' ( externalContext )* '}'
            {
            match(input,84,FOLLOW_84_in_externalContexts758); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_externalContexts760); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:738:22: ( externalContext )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==STRING_LITERAL) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:738:23: externalContext
            	    {
            	    pushFollow(FOLLOW_externalContext_in_externalContexts763);
            	    externalContext();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            match(input,101,FOLLOW_101_in_externalContexts767); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "externalContexts"



    // $ANTLR start "externalEntities"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:741:1: externalEntities : 'entities' '{' ( externalEntity )* '}' ;
    public final void externalEntities() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:742:5: ( 'entities' '{' ( externalEntity )* '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:742:7: 'entities' '{' ( externalEntity )* '}'
            {
            match(input,87,FOLLOW_87_in_externalEntities784); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_externalEntities786); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:742:22: ( externalEntity )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==STRING_LITERAL) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:742:23: externalEntity
            	    {
            	    pushFollow(FOLLOW_externalEntity_in_externalEntities789);
            	    externalEntity();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            match(input,101,FOLLOW_101_in_externalEntities793); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "externalEntities"



    // $ANTLR start "externalContext"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:745:1: externalContext : string ;
    public final void externalContext() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string1 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:746:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:746:8: string
            {
            pushFollow(FOLLOW_string_in_externalContext815);
            string1=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                    		if (logger.isInfoEnabled()) {
                				logger.info("external context '" + (string1!=null?input.toString(string1.start,string1.stop):null) + "'");
                			}
                			externalContext(GeneralUtils.trimQuotes((string1!=null?input.toString(string1.start,string1.stop):null)));
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "externalContext"



    // $ANTLR start "externalEntity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:754:1: externalEntity : string ;
    public final void externalEntity() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string2 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:755:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:755:8: string
            {
            pushFollow(FOLLOW_string_in_externalEntity836);
            string2=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                    		if (logger.isInfoEnabled()) {
                				logger.info("external entity '" + (string2!=null?input.toString(string2.start,string2.stop):null) + "'");
                			}
                			externalEntity(GeneralUtils.trimQuotes((string2!=null?input.toString(string2.start,string2.stop):null)));
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "externalEntity"



    // $ANTLR start "include"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:763:1: include : include_vwml ;
    public final void include() throws RecognitionException {
        String include_vwml3 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:764:5: ( include_vwml )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:764:7: include_vwml
            {
            pushFollow(FOLLOW_include_vwml_in_include856);
            include_vwml3=include_vwml();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (logger.isInfoEnabled()) {
                				logger.info("deferring include '" + include_vwml3 + "'");
                			}
                			deferInclude(GeneralUtils.trimQuotes(include_vwml3));    			
                               }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "include"



    // $ANTLR start "include_vwml"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:772:1: include_vwml returns [String id] : 'include' STRING_LITERAL ;
    public final String include_vwml() throws RecognitionException {
        String id = null;


        Token STRING_LITERAL4=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:773:5: ( 'include' STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:773:8: 'include' STRING_LITERAL
            {
            match(input,91,FOLLOW_91_in_include_vwml884); if (state.failed) return id;

            STRING_LITERAL4=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_include_vwml886); if (state.failed) return id;

            if ( state.backtracking==0 ) {id = (STRING_LITERAL4!=null?STRING_LITERAL4.getText():null);}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "include_vwml"



    // $ANTLR start "props"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:776:1: props : 'options' '{' optionsList '}' ;
    public final void props() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:777:5: ( 'options' '{' optionsList '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:777:7: 'options' '{' optionsList '}'
            {
            match(input,94,FOLLOW_94_in_props905); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_props907); if (state.failed) return ;

            pushFollow(FOLLOW_optionsList_in_props909);
            optionsList();

            state._fsp--;
            if (state.failed) return ;

            match(input,101,FOLLOW_101_in_props911); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "props"



    // $ANTLR start "optionsList"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:780:1: optionsList : lang ( conflictring )? ;
    public final void optionsList() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:781:5: ( lang ( conflictring )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:781:7: lang ( conflictring )?
            {
            pushFollow(FOLLOW_lang_in_optionsList932);
            lang();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:781:12: ( conflictring )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==82) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:781:12: conflictring
                    {
                    pushFollow(FOLLOW_conflictring_in_optionsList934);
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "optionsList"



    // $ANTLR start "lang"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:784:1: lang : ( ( 'language' '=' JAVA )=> langJava | otherLanguages );
    public final void lang() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:785:5: ( ( 'language' '=' JAVA )=> langJava | otherLanguages )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==92) && (synpred1_VirtualWorldModelingLanguage())) {
                alt11=1;
            }
            else if ( (LA11_0==82||LA11_0==101) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:785:7: ( 'language' '=' JAVA )=> langJava
                    {
                    pushFollow(FOLLOW_langJava_in_lang962);
                    langJava();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:786:7: otherLanguages
                    {
                    pushFollow(FOLLOW_otherLanguages_in_lang970);
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "lang"



    // $ANTLR start "otherLanguages"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:789:1: otherLanguages :;
    public final void otherLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:790:5: ()
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:791:5: 
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:793:1: langJava : 'language' '=' JAVA '{' javaProps '}' ;
    public final void langJava() throws RecognitionException {

               codeGenerator = vwmlModelBuilder.getCodeGenerator(VWMLModelBuilder.SINK_TYPE.JAVA);
               if (vwmlModelBuilder.getProjectProps() != null && vwmlModelBuilder.getProjectProps().getCodeGenerator() == null) {
               		vwmlModelBuilder.getProjectProps().setCodeGenerator(codeGenerator);
               }
               if (logger.isDebugEnabled()) {
               		logger.debug("Code generator '" + codeGenerator + "'");
               }
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:803:5: ( 'language' '=' JAVA '{' javaProps '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:803:7: 'language' '=' JAVA '{' javaProps '}'
            {
            match(input,92,FOLLOW_92_in_langJava1013); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_langJava1015); if (state.failed) return ;

            match(input,JAVA,FOLLOW_JAVA_in_langJava1017); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_langJava1019); if (state.failed) return ;

            pushFollow(FOLLOW_javaProps_in_langJava1021);
            javaProps();

            state._fsp--;
            if (state.failed) return ;

            match(input,101,FOLLOW_101_in_langJava1023); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "langJava"



    // $ANTLR start "javaProps"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:806:1: javaProps : propPackage ( generatedFileLocation )? optionalProps ;
    public final void javaProps() throws RecognitionException {

            	setupProps();
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:810:5: ( propPackage ( generatedFileLocation )? optionalProps )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:810:7: propPackage ( generatedFileLocation )? optionalProps
            {
            pushFollow(FOLLOW_propPackage_in_javaProps1049);
            propPackage();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:810:19: ( generatedFileLocation )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==96) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:810:19: generatedFileLocation
                    {
                    pushFollow(FOLLOW_generatedFileLocation_in_javaProps1051);
                    generatedFileLocation();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            pushFollow(FOLLOW_optionalProps_in_javaProps1054);
            optionalProps();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "javaProps"



    // $ANTLR start "propPackage"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:813:1: propPackage : 'package' '=' packageName ;
    public final void propPackage() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return packageName5 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:814:5: ( 'package' '=' packageName )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:814:7: 'package' '=' packageName
            {
            match(input,95,FOLLOW_95_in_propPackage1076); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_propPackage1078); if (state.failed) return ;

            pushFollow(FOLLOW_packageName_in_propPackage1080);
            packageName5=packageName();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			  if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModulePackage(GeneralUtils.trimQuotes((packageName5!=null?input.toString(packageName5.start,packageName5.stop):null)));
            	    			  }
                			        }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:821:1: packageName : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.packageName_return packageName() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return retval = new VirtualWorldModelingLanguageParser.packageName_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:822:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:822:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_packageName1099); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "packageName"



    // $ANTLR start "generatedFileLocation"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:1: generatedFileLocation : 'path' '=' path ;
    public final void generatedFileLocation() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return path6 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:826:5: ( 'path' '=' path )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:826:7: 'path' '=' path
            {
            match(input,96,FOLLOW_96_in_generatedFileLocation1116); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_generatedFileLocation1118); if (state.failed) return ;

            pushFollow(FOLLOW_path_in_generatedFileLocation1120);
            path6=path();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (modProps != null) {
                				((JavaCodeGenerator.JavaModuleStartProps)modProps).setSrcPath(GeneralUtils.trimQuotes((path6!=null?input.toString(path6.start,path6.stop):null)));
                			}
                		      }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "generatedFileLocation"



    // $ANTLR start "optionalProps"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:833:1: optionalProps : ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )? ;
    public final void optionalProps() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:5: ( ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:7: ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )?
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:7: ( author )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==79) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:7: author
                    {
                    pushFollow(FOLLOW_author_in_optionalProps1140);
                    author();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:15: ( projname )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==97) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:15: projname
                    {
                    pushFollow(FOLLOW_projname_in_optionalProps1143);
                    projname();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:25: ( description )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==86) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:25: description
                    {
                    pushFollow(FOLLOW_description_in_optionalProps1146);
                    description();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:38: ( entity_history_size )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==88) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:38: entity_history_size
                    {
                    pushFollow(FOLLOW_entity_history_size_in_optionalProps1149);
                    entity_history_size();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:59: ( visualizer )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==99) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:59: visualizer
                    {
                    pushFollow(FOLLOW_visualizer_in_optionalProps1152);
                    visualizer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:71: ( beyond_the_fringe )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==80) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:71: beyond_the_fringe
                    {
                    pushFollow(FOLLOW_beyond_the_fringe_in_optionalProps1155);
                    beyond_the_fringe();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:90: ( conflictring )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==82) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:834:90: conflictring
                    {
                    pushFollow(FOLLOW_conflictring_in_optionalProps1158);
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "optionalProps"



    // $ANTLR start "author"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:1: author : 'author' '=' string ;
    public final void author() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string7 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:838:5: ( 'author' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:838:7: 'author' '=' string
            {
            match(input,79,FOLLOW_79_in_author1176); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_author1178); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_author1180);
            string7=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setAuthor(GeneralUtils.trimQuotes((string7!=null?input.toString(string7.start,string7.stop):null)));
            	    			}
                			  }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "author"



    // $ANTLR start "projname"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:1: projname : 'project_name' '=' string ;
    public final void projname() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string8 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:846:5: ( 'project_name' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:846:7: 'project_name' '=' string
            {
            match(input,97,FOLLOW_97_in_projname1199); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_projname1201); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_projname1203);
            string8=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setProjectName(GeneralUtils.trimQuotes((string8!=null?input.toString(string8.start,string8.stop):null)));
            	    			}
                			  }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "projname"



    // $ANTLR start "description"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:853:1: description : 'description' '=' string ;
    public final void description() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string9 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:854:5: ( 'description' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:854:7: 'description' '=' string
            {
            match(input,86,FOLLOW_86_in_description1226); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_description1228); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_description1230);
            string9=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setDescription(GeneralUtils.trimQuotes((string9!=null?input.toString(string9.start,string9.stop):null)));
                				}
                			       }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "description"



    // $ANTLR start "entity_history_size"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:861:1: entity_history_size : 'entity_history_size' '=' string ;
    public final void entity_history_size() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string10 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:862:5: ( 'entity_history_size' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:862:7: 'entity_history_size' '=' string
            {
            match(input,88,FOLLOW_88_in_entity_history_size1249); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_entity_history_size1251); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_entity_history_size1253);
            string10=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setEntityHistorySize(GeneralUtils.trimQuotes((string10!=null?input.toString(string10.start,string10.stop):null)));
                				}
                			       }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entity_history_size"



    // $ANTLR start "visualizer"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:870:1: visualizer : 'visualizer' '{' visualizer_body '}' ;
    public final void visualizer() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:871:5: ( 'visualizer' '{' visualizer_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:871:7: 'visualizer' '{' visualizer_body '}'
            {
            match(input,99,FOLLOW_99_in_visualizer1273); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_visualizer1275); if (state.failed) return ;

            pushFollow(FOLLOW_visualizer_body_in_visualizer1277);
            visualizer_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,101,FOLLOW_101_in_visualizer1279); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer"



    // $ANTLR start "visualizer_body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:874:1: visualizer_body : ( visualizer_class visualizer_datapath |);
    public final void visualizer_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:875:5: ( visualizer_class visualizer_datapath |)
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==81) ) {
                alt20=1;
            }
            else if ( (LA20_0==101) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:875:7: visualizer_class visualizer_datapath
                    {
                    pushFollow(FOLLOW_visualizer_class_in_visualizer_body1297);
                    visualizer_class();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_visualizer_datapath_in_visualizer_body1299);
                    visualizer_datapath();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:877:5: 
                    {
                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer_body"



    // $ANTLR start "visualizer_class"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:879:1: visualizer_class : 'class' '=' string ;
    public final void visualizer_class() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string11 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:880:5: ( 'class' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:880:7: 'class' '=' string
            {
            match(input,81,FOLLOW_81_in_visualizer_class1322); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_visualizer_class1324); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_class1326);
            string11=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitor((AbstractVWMLLinkVisitor)GeneralUtils.instantiateClassThroughStaticMethod(GeneralUtils.trimQuotes((string11!=null?input.toString(string11.start,string11.stop):null)), "instance"));
                				}
                			 }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "visualizer_class"



    // $ANTLR start "visualizer_datapath"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:887:1: visualizer_datapath : 'data' '=' string ;
    public final void visualizer_datapath() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string12 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:888:5: ( 'data' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:888:7: 'data' '=' string
            {
            match(input,85,FOLLOW_85_in_visualizer_datapath1349); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_visualizer_datapath1351); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_datapath1353);
            string12=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setVisitorDataPath(GeneralUtils.trimQuotes((string12!=null?input.toString(string12.start,string12.stop):null)));
                				}
                			}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:895:1: path : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.path_return path() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return retval = new VirtualWorldModelingLanguageParser.path_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:896:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:896:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_path1376); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "path"



    // $ANTLR start "beyond_the_fringe"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:900:1: beyond_the_fringe : 'beyond' '{' beyond_the_fringe_body '}' ;
    public final void beyond_the_fringe() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:901:5: ( 'beyond' '{' beyond_the_fringe_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:901:7: 'beyond' '{' beyond_the_fringe_body '}'
            {
            match(input,80,FOLLOW_80_in_beyond_the_fringe1394); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_beyond_the_fringe1396); if (state.failed) return ;

            pushFollow(FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe1398);
            beyond_the_fringe_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,101,FOLLOW_101_in_beyond_the_fringe1400); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "beyond_the_fringe"



    // $ANTLR start "beyond_the_fringe_body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:904:1: beyond_the_fringe_body : finges ;
    public final void beyond_the_fringe_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:905:5: ( finges )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:905:8: finges
            {
            pushFollow(FOLLOW_finges_in_beyond_the_fringe_body1422);
            finges();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "beyond_the_fringe_body"



    // $ANTLR start "finges"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:908:1: finges : ( fringe )+ ;
    public final void finges() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:909:5: ( ( fringe )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:909:8: ( fringe )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:909:8: ( fringe )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==90) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:909:9: fringe
            	    {
            	    pushFollow(FOLLOW_fringe_in_finges1441);
            	    fringe();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "finges"



    // $ANTLR start "fringe"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:912:1: fringe : 'fringe' ID 'ias' '(' creatures ')' ;
    public final void fringe() throws RecognitionException {
        Token ID13=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:913:5: ( 'fringe' ID 'ias' '(' creatures ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:913:8: 'fringe' ID 'ias' '(' creatures ')'
            {
            match(input,90,FOLLOW_90_in_fringe1461); if (state.failed) return ;

            ID13=(Token)match(input,ID,FOLLOW_ID_in_fringe1463); if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_fringe1465); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			setActiveFringe(ID13.getText());
                		}

            match(input,76,FOLLOW_76_in_fringe1491); if (state.failed) return ;

            pushFollow(FOLLOW_creatures_in_fringe1493);
            creatures();

            state._fsp--;
            if (state.failed) return ;

            match(input,77,FOLLOW_77_in_fringe1495); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "fringe"



    // $ANTLR start "creatures"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:920:1: creatures : ( ( creature )+ | pfringedefblock | pswitch );
    public final void creatures() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:921:5: ( ( creature )+ | pfringedefblock | pswitch )
            int alt23=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt23=1;
                }
                break;
            case P_IF:
                {
                alt23=2;
                }
                break;
            case P_ELSE:
                {
                alt23=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }

            switch (alt23) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:921:7: ( creature )+
                    {
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:921:7: ( creature )+
                    int cnt22=0;
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==ID) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:921:8: creature
                    	    {
                    	    pushFollow(FOLLOW_creature_in_creatures1513);
                    	    creature();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt22 >= 1 ) break loop22;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(22, input);
                                throw eee;
                        }
                        cnt22++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:922:7: pfringedefblock
                    {
                    pushFollow(FOLLOW_pfringedefblock_in_creatures1523);
                    pfringedefblock();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:923:7: pswitch
                    {
                    pushFollow(FOLLOW_pswitch_in_creatures1531);
                    pswitch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creatures"



    // $ANTLR start "creature"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:926:1: creature : ID 'ias' string ;
    public final void creature() throws RecognitionException {
        Token ID14=null;
        VirtualWorldModelingLanguageParser.string_return string15 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:938:5: ( ID 'ias' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:938:7: ID 'ias' string
            {
            ID14=(Token)match(input,ID,FOLLOW_ID_in_creature1558); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				addLastDeclaredCreature(ID14.getText());
                			}
                	 	}

            match(input,IAS,FOLLOW_IAS_in_creature1563); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_creature1565);
            string15=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                	 		if (!skipOff()) {
                	 			addLastDeclaredCreatureProps(GeneralUtils.trimQuotes((string15!=null?input.toString(string15.start,string15.stop):null)));
                	 		}
                	 	}

            }

            if ( state.backtracking==0 ) {
            			if (codeGenerator != null && !skipOff()) {
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "creature"



    // $ANTLR start "conflictring"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:950:1: conflictring : 'conflictring' '{' ( conflictdef )* '}' ;
    public final void conflictring() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:951:5: ( 'conflictring' '{' ( conflictdef )* '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:951:7: 'conflictring' '{' ( conflictdef )* '}'
            {
            match(input,82,FOLLOW_82_in_conflictring1592); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_conflictring1594); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:951:26: ( conflictdef )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==STRING_LITERAL) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:951:26: conflictdef
            	    {
            	    pushFollow(FOLLOW_conflictdef_in_conflictring1596);
            	    conflictdef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            match(input,101,FOLLOW_101_in_conflictring1599); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "conflictring"



    // $ANTLR start "conflictdef"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:954:1: conflictdef : name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')' ;
    public final void conflictdef() throws RecognitionException {
        String name_of_conflict_on_ring16 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:955:5: ( name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:955:7: name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')'
            {
            pushFollow(FOLLOW_name_of_conflict_on_ring_in_conflictdef1617);
            name_of_conflict_on_ring16=name_of_conflict_on_ring();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { startConflictDefinitionOnRing(GeneralUtils.trimQuotes(name_of_conflict_on_ring16)); }

            match(input,83,FOLLOW_83_in_conflictdef1621); if (state.failed) return ;

            match(input,76,FOLLOW_76_in_conflictdef1623); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:955:138: ( name_of_related_conflict_on_ring )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==STRING_LITERAL) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:955:138: name_of_related_conflict_on_ring
            	    {
            	    pushFollow(FOLLOW_name_of_related_conflict_on_ring_in_conflictdef1625);
            	    name_of_related_conflict_on_ring();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);


            match(input,77,FOLLOW_77_in_conflictdef1628); if (state.failed) return ;

            if ( state.backtracking==0 ) { endConflictDefinitionOnRing(); }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "conflictdef"



    // $ANTLR start "name_of_conflict_on_ring"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:1: name_of_conflict_on_ring returns [String id] : string ;
    public final String name_of_conflict_on_ring() throws RecognitionException {
        String id = null;


        VirtualWorldModelingLanguageParser.string_return string17 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:959:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:959:7: string
            {
            pushFollow(FOLLOW_string_in_name_of_conflict_on_ring1655);
            string17=string();

            state._fsp--;
            if (state.failed) return id;

            if ( state.backtracking==0 ) { id = (string17!=null?input.toString(string17.start,string17.stop):null); }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "name_of_conflict_on_ring"



    // $ANTLR start "name_of_related_conflict_on_ring"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:962:1: name_of_related_conflict_on_ring : string ;
    public final void name_of_related_conflict_on_ring() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string18 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:963:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:963:7: string
            {
            pushFollow(FOLLOW_string_in_name_of_related_conflict_on_ring1676);
            string18=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { addConflictDefinitionOnRing(GeneralUtils.trimQuotes((string18!=null?input.toString(string18.start,string18.stop):null))); }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "name_of_related_conflict_on_ring"



    // $ANTLR start "module"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:966:1: module : 'module' ID body ;
    public final void module() throws RecognitionException {
        Token ID19=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:967:5: ( 'module' ID body )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:967:7: 'module' ID body
            {
            match(input,93,FOLLOW_93_in_module1695); if (state.failed) return ;

            ID19=(Token)match(input,ID,FOLLOW_ID_in_module1697); if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                			modName = ID19.getText();
                			logger.info("Compiling module '" + modName + "'");
                			setupProps();
                			// normalizes module's properties; if some properties were not set they are filled by project's properties
                			// ... so it is way to override them 
                			modProps = vwmlModelBuilder.normalizeProps(modProps);
                			// associates module's name with module info structure (will be used on last dource generation phase, especially during unit-test generation)
                			vwmlModelBuilder.addModuleInfo(modName, VWMLModuleInfo.build(modProps, null));
                			if (modProps != null) {
                				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModuleName(modName);
            	    			((JavaCodeGenerator.JavaModuleStartProps)modProps).setSourceName(getSourceName());
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

            pushFollow(FOLLOW_body_in_module1701);
            body();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "module"



    // $ANTLR start "body"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:998:1: body : '{' ( expression ( expression )* )? '}' ;
    public final void body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:999:4: ( '{' ( expression ( expression )* )? '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:999:6: '{' ( expression ( expression )* )? '}'
            {
            match(input,100,FOLLOW_100_in_body1721); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:999:10: ( expression ( expression )* )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==ID||LA27_0==LIFETERM||LA27_0==P_ELSE||LA27_0==P_IF||LA27_0==76||LA27_0==78||LA27_0==98) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:999:11: expression ( expression )*
                    {
                    pushFollow(FOLLOW_expression_in_body1724);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:999:22: ( expression )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==ID||LA26_0==LIFETERM||LA26_0==P_ELSE||LA26_0==P_IF||LA26_0==76||LA26_0==78||LA26_0==98) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:999:23: expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_body1727);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,101,FOLLOW_101_in_body1733); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "body"



    // $ANTLR start "expression"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1003:1: expression : ( ( bunch_of_entity_decls IAS )=> entity_def | check_term_def | pvwmlblock | pswitch );
    public final void expression() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1004:5: ( ( bunch_of_entity_decls IAS )=> entity_def | check_term_def | pvwmlblock | pswitch )
            int alt28=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA28_1 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt28=1;
                }
                else if ( (true) ) {
                    alt28=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 28, 1, input);

                    throw nvae;

                }
                }
                break;
            case 76:
                {
                int LA28_2 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt28=1;
                }
                else if ( (true) ) {
                    alt28=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 28, 2, input);

                    throw nvae;

                }
                }
                break;
            case LIFETERM:
            case 78:
            case 98:
                {
                alt28=2;
                }
                break;
            case P_IF:
                {
                alt28=3;
                }
                break;
            case P_ELSE:
                {
                alt28=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }

            switch (alt28) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1004:7: ( bunch_of_entity_decls IAS )=> entity_def
                    {
                    pushFollow(FOLLOW_entity_def_in_expression1759);
                    entity_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1005:7: check_term_def
                    {
                    pushFollow(FOLLOW_check_term_def_in_expression1767);
                    check_term_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1006:7: pvwmlblock
                    {
                    pushFollow(FOLLOW_pvwmlblock_in_expression1775);
                    pvwmlblock();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1007:7: pswitch
                    {
                    pushFollow(FOLLOW_pswitch_in_expression1783);
                    pswitch();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "expression"



    // $ANTLR start "entity_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1010:1: entity_def : bunch_of_entity_decls IAS ( term )* SEMICOLON ;
    public final void entity_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1011:5: ( bunch_of_entity_decls IAS ( term )* SEMICOLON )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1011:7: bunch_of_entity_decls IAS ( term )* SEMICOLON
            {
            pushFollow(FOLLOW_bunch_of_entity_decls_in_entity_def1800);
            bunch_of_entity_decls();

            state._fsp--;
            if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_entity_def1802); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
            	    			// adds entity id to context stack
            	    			declareAbsoluteContextByIASRelation();
                			}
                		}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1017:9: ( term )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==ID||LA29_0==LIFETERM||LA29_0==P_ELSE||LA29_0==P_IF||LA29_0==76||LA29_0==78||LA29_0==98) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1017:10: term
            	    {
            	    pushFollow(FOLLOW_term_in_entity_def1813);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entity_def1817); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		      	if (!skipOff()) {
                		      		// removes top entity from stack
                		      		handleProcessedAbsoluteContextbyIASRelation();
                		      	}
            		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entity_def"



    // $ANTLR start "check_term_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1027:1: check_term_def : ( ( source_lifetrerm )? LIFETERM '=' lifeterm_def | term_def );
    public final void check_term_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1028:5: ( ( source_lifetrerm )? LIFETERM '=' lifeterm_def | term_def )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==LIFETERM||LA31_0==98) ) {
                alt31=1;
            }
            else if ( (LA31_0==ID||LA31_0==76||LA31_0==78) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1028:7: ( source_lifetrerm )? LIFETERM '=' lifeterm_def
                    {
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1028:7: ( source_lifetrerm )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==98) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1028:7: source_lifetrerm
                            {
                            pushFollow(FOLLOW_source_lifetrerm_in_check_term_def1851);
                            source_lifetrerm();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,LIFETERM,FOLLOW_LIFETERM_in_check_term_def1854); if (state.failed) return ;

                    match(input,P_OP_E,FOLLOW_P_OP_E_in_check_term_def1856); if (state.failed) return ;

                    pushFollow(FOLLOW_lifeterm_def_in_check_term_def1858);
                    lifeterm_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1029:7: term_def
                    {
                    pushFollow(FOLLOW_term_def_in_check_term_def1866);
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "check_term_def"



    // $ANTLR start "source_lifetrerm"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1032:1: source_lifetrerm : 'source' ;
    public final void source_lifetrerm() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1033:5: ( 'source' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1033:7: 'source'
            {
            match(input,98,FOLLOW_98_in_source_lifetrerm1883); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				if (logger.isDebugEnabled()) {
                					logger.debug("source lifeterm indicator detected");
                				}
                				sourceLifeTermDetectedFlag = true;
                			}
                	       }

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "source_lifetrerm"



    // $ANTLR start "lifeterm_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1043:1: lifeterm_def : term_def ;
    public final void lifeterm_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1044:5: ( term_def )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1044:8: term_def
            {
            pushFollow(FOLLOW_term_def_in_lifeterm_def1903);
            term_def();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
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

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "lifeterm_def"



    // $ANTLR start "term_def"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1065:1: term_def : entity ( oplist )* ;
    public final void term_def() throws RecognitionException {
        EntityWalker.Relation entity20 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1066:5: ( entity ( oplist )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1066:7: entity ( oplist )*
            {
            pushFollow(FOLLOW_entity_in_term_def1922);
            entity20=entity();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				lastProcessedEntity = entity20;
                				lastProcessedEntityAsTerm = false;
                				if (lastProcessedEntity != null && logger.isDebugEnabled()) {
                					logger.debug(">> '" + lastProcessedEntity.getObj() + "' <<");
                				}
                			}
                	     	}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1074:14: ( oplist )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( ((LA32_0 >= OPACTIVATE && LA32_0 <= OPSUBSTRUCT)) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1074:15: oplist
            	    {
            	    pushFollow(FOLLOW_oplist_in_term_def1928);
            	    oplist();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            if ( state.backtracking==0 ) {  
              	       		if (lastProcessedEntityAsTerm && codeGenerator != null && !skipOff()) {
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "term_def"



    // $ANTLR start "entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1094:1: entity_decl : ( simple_entity_decl | complex_entity_decl );
    public final void entity_decl() throws RecognitionException {
        String simple_entity_decl21 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1095:5: ( simple_entity_decl | complex_entity_decl )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==ID) ) {
                alt33=1;
            }
            else if ( (LA33_0==76) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }
            switch (alt33) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1095:7: simple_entity_decl
                    {
                    pushFollow(FOLLOW_simple_entity_decl_in_entity_decl1959);
                    simple_entity_decl21=simple_entity_decl();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			if (!skipOff() && !complexEntityNameBuilderDecl.isInProgress()) {
                        				lastProcessedContextBunch.add(ContextBunchElement.build(simple_entity_decl21));
                        				if (logger.isDebugEnabled()) {
                        					logger.debug("+++++++++++++++++++++++ " + simple_entity_decl21);
                        				}
                        			}
                        		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1104:7: complex_entity_decl
                    {
                    pushFollow(FOLLOW_complex_entity_decl_in_entity_decl1975);
                    complex_entity_decl();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			if (!skipOff() && complexEntityNameBuilderDecl.isRootEntityFinishedProgress()) {
                      				Object id = complexEntityDeclarationPhase3();
                        				lastProcessedContextBunch.add(ContextBunchElement.build(id));
                        			}
                        		}

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "entity_decl"



    // $ANTLR start "bunch_of_entity_decls"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1113:1: bunch_of_entity_decls : entity_decl ( COMMA entity_decl )* ;
    public final void bunch_of_entity_decls() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1123:5: ( entity_decl ( COMMA entity_decl )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1124:7: entity_decl ( COMMA entity_decl )*
            {
            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				lastProcessedContextBunch = VWMLContextBuilder.ContextBunch.instance();
                				if (logger.isDebugEnabled()) {
                					logger.debug("Created bunch");
                				}
                			}
                		}

            pushFollow(FOLLOW_entity_decl_in_bunch_of_entity_decls2019);
            entity_decl();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1131:21: ( COMMA entity_decl )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==COMMA) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1131:22: COMMA entity_decl
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_bunch_of_entity_decls2022); if (state.failed) return ;

            	    pushFollow(FOLLOW_entity_decl_in_bunch_of_entity_decls2024);
            	    entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
                    			vwmlContextBuilder.push(lastProcessedContextBunch);
                    			if (logger.isDebugEnabled()) {
                    				logger.debug("Pushed '" + lastProcessedContextBunch + "'; parent contexts '" + contexts + "'");
                    			}
                    		}
                		}
        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "bunch_of_entity_decls"



    // $ANTLR start "simple_entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1135:1: simple_entity_decl returns [String id] : ID ;
    public final String simple_entity_decl() throws RecognitionException {
        String id = null;


        Token ID22=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1136:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1136:7: ID
            {
            ID22=(Token)match(input,ID,FOLLOW_ID_in_simple_entity_decl2053); if (state.failed) return id;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				id = simpleEntityDeclaration(ID22.getText());
                			}
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return id;
    }
    // $ANTLR end "simple_entity_decl"



    // $ANTLR start "complex_entity_decl"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1143:1: complex_entity_decl : '(' ( entity_decl )* ')' ;
    public final void complex_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1144:5: ( '(' ( entity_decl )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1144:7: '(' ( entity_decl )* ')'
            {
            match(input,76,FOLLOW_76_in_complex_entity_decl2077); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				complexEntityDeclarationPhase1();
                			}
                		}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1148:9: ( entity_decl )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==ID||LA35_0==76) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1148:10: entity_decl
            	    {
            	    pushFollow(FOLLOW_entity_decl_in_complex_entity_decl2083);
            	    entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				complexEntityDeclarationPhase2();
                			}
                		}

            match(input,77,FOLLOW_77_in_complex_entity_decl2100); if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "complex_entity_decl"



    // $ANTLR start "term"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1157:1: term : expression ;
    public final void term() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1158:5: ( expression )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1158:7: expression
            {
            pushFollow(FOLLOW_expression_in_term2117);
            expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "term"



    // $ANTLR start "entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1161:1: entity returns [EntityWalker.Relation rel] : ( simple_entity | complex_entity | '.' );
    public final EntityWalker.Relation entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation simple_entity23 =null;

        EntityWalker.Relation complex_entity24 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1162:5: ( simple_entity | complex_entity | '.' )
            int alt36=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt36=1;
                }
                break;
            case 76:
                {
                alt36=2;
                }
                break;
            case 78:
                {
                alt36=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return rel;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;

            }

            switch (alt36) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1162:7: simple_entity
                    {
                    pushFollow(FOLLOW_simple_entity_in_entity2140);
                    simple_entity23=simple_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) { 
                        			rel = simple_entity23;
                        		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1167:7: complex_entity
                    {
                    pushFollow(FOLLOW_complex_entity_in_entity2157);
                    complex_entity24=complex_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) { 
                        			rel = complex_entity24;
                        		}

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1171:7: '.'
                    {
                    match(input,78,FOLLOW_78_in_entity2173); if (state.failed) return rel;

                    if ( state.backtracking==0 ) {
                                    	if (!skipOff()) {
                                    		processComplexContext(lastProcessedEntity);
                                    	}
                                 	}

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "entity"



    // $ANTLR start "simple_entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1179:1: simple_entity returns [EntityWalker.Relation rel] : ID ;
    public final EntityWalker.Relation simple_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        Token ID25=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1180:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1180:7: ID
            {
            ID25=(Token)match(input,ID,FOLLOW_ID_in_simple_entity2203); if (state.failed) return rel;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				rel = simpleEntityAssembling((ID25!=null?ID25.getText():null));
                			} else {
                				rel = null;
                			}
                     	}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "simple_entity"



    // $ANTLR start "complex_entity"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1189:1: complex_entity returns [EntityWalker.Relation rel] : '(' ( term )* ')' ;
    public final EntityWalker.Relation complex_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;



            			if (!skipOff()) {
            				complexEntityStartAssembling();
            			}
            		
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1200:5: ( '(' ( term )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1200:7: '(' ( term )* ')'
            {
            match(input,76,FOLLOW_76_in_complex_entity2247); if (state.failed) return rel;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1200:11: ( term )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==ID||LA37_0==LIFETERM||LA37_0==P_ELSE||LA37_0==P_IF||LA37_0==76||LA37_0==78||LA37_0==98) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1200:12: term
            	    {
            	    pushFollow(FOLLOW_term_in_complex_entity2250);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return rel;

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            match(input,77,FOLLOW_77_in_complex_entity2254); if (state.failed) return rel;

            }

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                    			rel = complexEntityStopAssembling();
                    		}
                		}
        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "complex_entity"



    // $ANTLR start "oplist"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1213:1: oplist : opclist ;
    public final void oplist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return opclist26 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1215:5: ( opclist )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1215:7: opclist
            {
            pushFollow(FOLLOW_opclist_in_oplist2339);
            opclist26=opclist();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && lastProcessedEntity != null && codeGenerator != null) { 
                				lastProcessedEntityAsTerm = true;
                				VWMLContextBuilder.Contexts contexts = vwmlContextBuilder.buildContext();
                				String c = contexts.first();
                				com.vw.lang.sink.OperationInfo opInfo = new com.vw.lang.sink.OperationInfo();
                				org.antlr.runtime.Token nextToken = getTokenStream().LT(1);
                				opInfo.setOpCode((opclist26!=null?input.toString(opclist26.start,opclist26.stop):null));
                				opInfo.setLine(nextToken.getLine());
                				opInfo.setPosition(nextToken.getCharPositionInLine());
                				opInfo.setFileName(getSourceName());
                				opInfo.setNextToken(nextToken.getText());
                				codeGenerator.associateOperation(lastProcessedEntity, (opclist26!=null?input.toString(opclist26.start,opclist26.stop):null), c, opInfo);
                			} 
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1233:1: opclist : ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM | OPACTIVATECTX | OPACTIVATEONFRINGE | OPRELAX | OPSTARTCONFLICTGROUP | OPENDCONFLICTGROUP | OPBREAKPOINT | OPAPPLYTOCONTEXT | OPCLONE | OPBORN | OPPROJECTION | OPFOREACH | OPDYNCONTEXT | OPSIZE | OPINTERRUPT | OPCALLP | OPGET | OPFIND | OPGATE | OPRECALL | OPREPEAT | OPACTIVATE | OPCOPY | OPSTARTINTERCEPTION | OPFINISHINTERCEPTION | OPSTARTINTERCEPTION_S | OPFINISHINTERCEPTION_S | OPRELEASE | OPEXISTSI | OPEXISTSI_S | OPLTT );
    public final VirtualWorldModelingLanguageParser.opclist_return opclist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return retval = new VirtualWorldModelingLanguageParser.opclist_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1234:5: ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM | OPACTIVATECTX | OPACTIVATEONFRINGE | OPRELAX | OPSTARTCONFLICTGROUP | OPENDCONFLICTGROUP | OPBREAKPOINT | OPAPPLYTOCONTEXT | OPCLONE | OPBORN | OPPROJECTION | OPFOREACH | OPDYNCONTEXT | OPSIZE | OPINTERRUPT | OPCALLP | OPGET | OPFIND | OPGATE | OPRECALL | OPREPEAT | OPACTIVATE | OPCOPY | OPSTARTINTERCEPTION | OPFINISHINTERCEPTION | OPSTARTINTERCEPTION_S | OPFINISHINTERCEPTION_S | OPRELEASE | OPEXISTSI | OPEXISTSI_S | OPLTT )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:
            {
            if ( (input.LA(1) >= OPACTIVATE && input.LA(1) <= OPSUBSTRUCT) ) {
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "opclist"



    // $ANTLR start "termLanguages"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1283:1: termLanguages : ( JAVA | C | CPP | OBJECTIVEC );
    public final void termLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1284:5: ( JAVA | C | CPP | OBJECTIVEC )
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
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1290:1: string : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.string_return string() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return retval = new VirtualWorldModelingLanguageParser.string_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1291:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1291:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string2794); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "string"



    // $ANTLR start "pfringedefblock"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1296:1: pfringedefblock : pstart pexpressions creatures pend ;
    public final void pfringedefblock() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1297:5: ( pstart pexpressions creatures pend )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1297:7: pstart pexpressions creatures pend
            {
            pushFollow(FOLLOW_pstart_in_pfringedefblock2813);
            pstart();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_pexpressions_in_pfringedefblock2815);
            pexpressions();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.processDirectiveIf();
                		}

            pushFollow(FOLLOW_creatures_in_pfringedefblock2832);
            creatures();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_pend_in_pfringedefblock2840);
            pend();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "pfringedefblock"



    // $ANTLR start "pvwmlblock"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1306:1: pvwmlblock : pstart pexpressions ( expression )* pend ;
    public final void pvwmlblock() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1307:5: ( pstart pexpressions ( expression )* pend )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1307:7: pstart pexpressions ( expression )* pend
            {
            pushFollow(FOLLOW_pstart_in_pvwmlblock2858);
            pstart();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_pexpressions_in_pvwmlblock2860);
            pexpressions();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                			preprocessor.processDirectiveIf();
                		}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1311:7: ( expression )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==ID||LA38_0==LIFETERM||LA38_0==P_ELSE||LA38_0==P_IF||LA38_0==76||LA38_0==78||LA38_0==98) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1311:8: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_pvwmlblock2879);
            	    expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            pushFollow(FOLLOW_pend_in_pvwmlblock2889);
            pend();

            state._fsp--;
            if (state.failed) return ;

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "pvwmlblock"



    // $ANTLR start "pexpressions"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1315:1: pexpressions : '(' ( pexpression )* ')' ;
    public final void pexpressions() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1316:5: ( '(' ( pexpression )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1316:7: '(' ( pexpression )* ')'
            {
            match(input,76,FOLLOW_76_in_pexpressions2906); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.getTopDirectiveIf().addExpressionItem();
                		}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1319:6: ( pexpression )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==ID) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1319:7: pexpression
            	    {
            	    pushFollow(FOLLOW_pexpression_in_pexpressions2917);
            	    pexpression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            match(input,77,FOLLOW_77_in_pexpressions2928); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.getTopDirectiveIf().removeTop();
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "pexpressions"



    // $ANTLR start "pstart"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1325:1: pstart : P_IF ;
    public final void pstart() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1326:5: ( P_IF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1326:7: P_IF
            {
            match(input,P_IF,FOLLOW_P_IF_in_pstart2947); if (state.failed) return ;

            if ( state.backtracking==0 ) {
               			preprocessor.startDirectiveIf();
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "pstart"



    // $ANTLR start "pend"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1331:1: pend : P_ENDIF ;
    public final void pend() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1332:5: ( P_ENDIF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1332:7: P_ENDIF
            {
            match(input,P_ENDIF,FOLLOW_P_ENDIF_in_pend2967); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.endDirectiveIf();
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "pend"



    // $ANTLR start "pswitch"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1338:1: pswitch : P_ELSE ;
    public final void pswitch() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1339:5: ( P_ELSE )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1339:7: P_ELSE
            {
            match(input,P_ELSE,FOLLOW_P_ELSE_in_pswitch2993); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.reverseResultOfProcessingDirectiveIf();
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "pswitch"



    // $ANTLR start "pexpression"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1344:1: pexpression : pitem ( poperation ( pexpressions )* )* ;
    public final void pexpression() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1345:5: ( pitem ( poperation ( pexpressions )* )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1345:7: pitem ( poperation ( pexpressions )* )*
            {
            pushFollow(FOLLOW_pitem_in_pexpression3014);
            pitem();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1345:13: ( poperation ( pexpressions )* )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( ((LA41_0 >= P_OP_AND && LA41_0 <= P_OP_OR)) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1345:14: poperation ( pexpressions )*
            	    {
            	    pushFollow(FOLLOW_poperation_in_pexpression3017);
            	    poperation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1345:25: ( pexpressions )*
            	    loop40:
            	    do {
            	        int alt40=2;
            	        int LA40_0 = input.LA(1);

            	        if ( (LA40_0==76) ) {
            	            alt40=1;
            	        }


            	        switch (alt40) {
            	    	case 1 :
            	    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1345:26: pexpressions
            	    	    {
            	    	    pushFollow(FOLLOW_pexpressions_in_pexpression3020);
            	    	    pexpressions();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop40;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "pexpression"



    // $ANTLR start "pitem"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1348:1: pitem : ID ;
    public final void pitem() throws RecognitionException {
        Token ID27=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1349:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1349:7: ID
            {
            ID27=(Token)match(input,ID,FOLLOW_ID_in_pitem3045); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.getTopDirectiveIf().addRegularItem((ID27!=null?ID27.getText():null));
                		}

            }

        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "pitem"



    // $ANTLR start "poperation"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1354:1: poperation : ( P_OP_AND | P_OP_OR | P_OP_B | P_OP_L | P_OP_E );
    public final void poperation() throws RecognitionException {
        Token P_OP_AND28=null;
        Token P_OP_OR29=null;
        Token P_OP_B30=null;
        Token P_OP_L31=null;
        Token P_OP_E32=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1355:5: ( P_OP_AND | P_OP_OR | P_OP_B | P_OP_L | P_OP_E )
            int alt42=5;
            switch ( input.LA(1) ) {
            case P_OP_AND:
                {
                alt42=1;
                }
                break;
            case P_OP_OR:
                {
                alt42=2;
                }
                break;
            case P_OP_B:
                {
                alt42=3;
                }
                break;
            case P_OP_L:
                {
                alt42=4;
                }
                break;
            case P_OP_E:
                {
                alt42=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }

            switch (alt42) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1355:7: P_OP_AND
                    {
                    P_OP_AND28=(Token)match(input,P_OP_AND,FOLLOW_P_OP_AND_in_poperation3064); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_AND28!=null?P_OP_AND28.getText():null));
                        		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1358:7: P_OP_OR
                    {
                    P_OP_OR29=(Token)match(input,P_OP_OR,FOLLOW_P_OP_OR_in_poperation3074); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_OR29!=null?P_OP_OR29.getText():null));
                        		}

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1361:7: P_OP_B
                    {
                    P_OP_B30=(Token)match(input,P_OP_B,FOLLOW_P_OP_B_in_poperation3084); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_B30!=null?P_OP_B30.getText():null));
                        		}

                    }
                    break;
                case 4 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1364:7: P_OP_L
                    {
                    P_OP_L31=(Token)match(input,P_OP_L,FOLLOW_P_OP_L_in_poperation3094); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_L31!=null?P_OP_L31.getText():null));
                        		}

                    }
                    break;
                case 5 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1367:7: P_OP_E
                    {
                    P_OP_E32=(Token)match(input,P_OP_E,FOLLOW_P_OP_E_in_poperation3104); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_E32!=null?P_OP_E32.getText():null));
                        		}

                    }
                    break;

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
            catch (Exception e) {
            	rethrowVWMLExceptionAsRecognitionException(e);
            }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "poperation"

    // $ANTLR start synpred1_VirtualWorldModelingLanguage
    public final void synpred1_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:785:7: ( 'language' '=' JAVA )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:785:8: 'language' '=' JAVA
        {
        match(input,92,FOLLOW_92_in_synpred1_VirtualWorldModelingLanguage953); if (state.failed) return ;

        match(input,P_OP_E,FOLLOW_P_OP_E_in_synpred1_VirtualWorldModelingLanguage955); if (state.failed) return ;

        match(input,JAVA,FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage957); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_VirtualWorldModelingLanguage

    // $ANTLR start synpred2_VirtualWorldModelingLanguage
    public final void synpred2_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1004:7: ( bunch_of_entity_decls IAS )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1004:8: bunch_of_entity_decls IAS
        {
        pushFollow(FOLLOW_bunch_of_entity_decls_in_synpred2_VirtualWorldModelingLanguage1752);
        bunch_of_entity_decls();

        state._fsp--;
        if (state.failed) return ;

        match(input,IAS,FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1754); if (state.failed) return ;

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


 

    public static final BitSet FOLLOW_props_in_filedef673 = new BitSet(new long[]{0x0000000000000000L,0x000000002A000000L});
    public static final BitSet FOLLOW_include_in_filedef677 = new BitSet(new long[]{0x0000000000000000L,0x000000002A000000L});
    public static final BitSet FOLLOW_include_in_filedef680 = new BitSet(new long[]{0x0000000000000000L,0x000000002A000000L});
    public static final BitSet FOLLOW_external_in_filedef686 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_module_in_filedef689 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_filedef692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_external713 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_external715 = new BitSet(new long[]{0x0000000000000000L,0x0000002000900000L});
    public static final BitSet FOLLOW_externalBody_in_external717 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_external719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalContexts_in_externalBody736 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_externalEntities_in_externalBody739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_externalContexts758 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_externalContexts760 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000400L});
    public static final BitSet FOLLOW_externalContext_in_externalContexts763 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000400L});
    public static final BitSet FOLLOW_101_in_externalContexts767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_externalEntities784 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_externalEntities786 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000400L});
    public static final BitSet FOLLOW_externalEntity_in_externalEntities789 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000400L});
    public static final BitSet FOLLOW_101_in_externalEntities793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_externalContext815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_externalEntity836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_include_vwml_in_include856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_include_vwml884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_include_vwml886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_props905 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_props907 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_optionsList_in_props909 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_props911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lang_in_optionsList932 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_conflictring_in_optionsList934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_langJava_in_lang962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_otherLanguages_in_lang970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_langJava1013 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_langJava1015 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_langJava1017 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_langJava1019 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_javaProps_in_langJava1021 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_langJava1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_propPackage_in_javaProps1049 = new BitSet(new long[]{0x0000000000000000L,0x0000000B01458000L});
    public static final BitSet FOLLOW_generatedFileLocation_in_javaProps1051 = new BitSet(new long[]{0x0000000000000000L,0x0000000A01458000L});
    public static final BitSet FOLLOW_optionalProps_in_javaProps1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_propPackage1076 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_propPackage1078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_packageName_in_propPackage1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_packageName1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_generatedFileLocation1116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_generatedFileLocation1118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_path_in_generatedFileLocation1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_author_in_optionalProps1140 = new BitSet(new long[]{0x0000000000000002L,0x0000000A01450000L});
    public static final BitSet FOLLOW_projname_in_optionalProps1143 = new BitSet(new long[]{0x0000000000000002L,0x0000000801450000L});
    public static final BitSet FOLLOW_description_in_optionalProps1146 = new BitSet(new long[]{0x0000000000000002L,0x0000000801050000L});
    public static final BitSet FOLLOW_entity_history_size_in_optionalProps1149 = new BitSet(new long[]{0x0000000000000002L,0x0000000800050000L});
    public static final BitSet FOLLOW_visualizer_in_optionalProps1152 = new BitSet(new long[]{0x0000000000000002L,0x0000000000050000L});
    public static final BitSet FOLLOW_beyond_the_fringe_in_optionalProps1155 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_conflictring_in_optionalProps1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_author1176 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_author1178 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_author1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_projname1199 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_projname1201 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_projname1203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_description1226 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_description1228 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_description1230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_entity_history_size1249 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_entity_history_size1251 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_entity_history_size1253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_visualizer1273 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_visualizer1275 = new BitSet(new long[]{0x0000000000000000L,0x0000002000020000L});
    public static final BitSet FOLLOW_visualizer_body_in_visualizer1277 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_visualizer1279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_visualizer_class_in_visualizer_body1297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_visualizer_datapath_in_visualizer_body1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_visualizer_class1322 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_visualizer_class1324 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_visualizer_class1326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_visualizer_datapath1349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_visualizer_datapath1351 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_visualizer_datapath1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_path1376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_beyond_the_fringe1394 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_beyond_the_fringe1396 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe1398 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_beyond_the_fringe1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finges_in_beyond_the_fringe_body1422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fringe_in_finges1441 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_fringe1461 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_fringe1463 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_fringe1465 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_fringe1491 = new BitSet(new long[]{0x0000000000000400L,0x000000000000000AL});
    public static final BitSet FOLLOW_creatures_in_fringe1493 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_fringe1495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creature_in_creatures1513 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_pfringedefblock_in_creatures1523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pswitch_in_creatures1531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_creature1558 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_creature1563 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_creature1565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_conflictring1592 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_conflictring1594 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000400L});
    public static final BitSet FOLLOW_conflictdef_in_conflictring1596 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000400L});
    public static final BitSet FOLLOW_101_in_conflictring1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_of_conflict_on_ring_in_conflictdef1617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_conflictdef1621 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_conflictdef1623 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_name_of_related_conflict_on_ring_in_conflictdef1625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002400L});
    public static final BitSet FOLLOW_77_in_conflictdef1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_name_of_conflict_on_ring1655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_name_of_related_conflict_on_ring1676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_module1695 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_module1697 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_body_in_module1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_body1721 = new BitSet(new long[]{0x0000000000002400L,0x000000240000500AL});
    public static final BitSet FOLLOW_expression_in_body1724 = new BitSet(new long[]{0x0000000000002400L,0x000000240000500AL});
    public static final BitSet FOLLOW_expression_in_body1727 = new BitSet(new long[]{0x0000000000002400L,0x000000240000500AL});
    public static final BitSet FOLLOW_101_in_body1733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_def_in_expression1759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_term_def_in_expression1767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pvwmlblock_in_expression1775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pswitch_in_expression1783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bunch_of_entity_decls_in_entity_def1800 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_entity_def1802 = new BitSet(new long[]{0x0000000000002400L,0x000000040000520AL});
    public static final BitSet FOLLOW_term_in_entity_def1813 = new BitSet(new long[]{0x0000000000002400L,0x000000040000520AL});
    public static final BitSet FOLLOW_SEMICOLON_in_entity_def1817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_source_lifetrerm_in_check_term_def1851 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LIFETERM_in_check_term_def1854 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_check_term_def1856 = new BitSet(new long[]{0x0000000000000400L,0x0000000000005000L});
    public static final BitSet FOLLOW_lifeterm_def_in_check_term_def1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_check_term_def1866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_source_lifetrerm1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_lifeterm_def1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_term_def1922 = new BitSet(new long[]{0xFFFFFFFFFFFE0002L});
    public static final BitSet FOLLOW_oplist_in_term_def1928 = new BitSet(new long[]{0xFFFFFFFFFFFE0002L});
    public static final BitSet FOLLOW_simple_entity_decl_in_entity_decl1959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_decl_in_entity_decl1975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_bunch_of_entity_decls2019 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_bunch_of_entity_decls2022 = new BitSet(new long[]{0x0000000000000400L,0x0000000000001000L});
    public static final BitSet FOLLOW_entity_decl_in_bunch_of_entity_decls2024 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ID_in_simple_entity_decl2053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_complex_entity_decl2077 = new BitSet(new long[]{0x0000000000000400L,0x0000000000003000L});
    public static final BitSet FOLLOW_entity_decl_in_complex_entity_decl2083 = new BitSet(new long[]{0x0000000000000400L,0x0000000000003000L});
    public static final BitSet FOLLOW_77_in_complex_entity_decl2100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_term2117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_entity_in_entity2140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_in_entity2157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_entity2173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity2203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_complex_entity2247 = new BitSet(new long[]{0x0000000000002400L,0x000000040000700AL});
    public static final BitSet FOLLOW_term_in_complex_entity2250 = new BitSet(new long[]{0x0000000000002400L,0x000000040000700AL});
    public static final BitSet FOLLOW_77_in_complex_entity2254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opclist_in_oplist2339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string2794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pstart_in_pfringedefblock2813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_pexpressions_in_pfringedefblock2815 = new BitSet(new long[]{0x0000000000000400L,0x000000000000000AL});
    public static final BitSet FOLLOW_creatures_in_pfringedefblock2832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_pend_in_pfringedefblock2840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pstart_in_pvwmlblock2858 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_pexpressions_in_pvwmlblock2860 = new BitSet(new long[]{0x0000000000002400L,0x000000040000500EL});
    public static final BitSet FOLLOW_expression_in_pvwmlblock2879 = new BitSet(new long[]{0x0000000000002400L,0x000000040000500EL});
    public static final BitSet FOLLOW_pend_in_pvwmlblock2889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_pexpressions2906 = new BitSet(new long[]{0x0000000000000400L,0x0000000000002000L});
    public static final BitSet FOLLOW_pexpression_in_pexpressions2917 = new BitSet(new long[]{0x0000000000000400L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_pexpressions2928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_IF_in_pstart2947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_ENDIF_in_pend2967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_ELSE_in_pswitch2993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pitem_in_pexpression3014 = new BitSet(new long[]{0x0000000000000002L,0x00000000000001F0L});
    public static final BitSet FOLLOW_poperation_in_pexpression3017 = new BitSet(new long[]{0x0000000000000002L,0x00000000000011F0L});
    public static final BitSet FOLLOW_pexpressions_in_pexpression3020 = new BitSet(new long[]{0x0000000000000002L,0x00000000000011F0L});
    public static final BitSet FOLLOW_ID_in_pitem3045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_AND_in_poperation3064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_OR_in_poperation3074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_B_in_poperation3084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_L_in_poperation3094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_E_in_poperation3104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_synpred1_VirtualWorldModelingLanguage953 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_synpred1_VirtualWorldModelingLanguage955 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bunch_of_entity_decls_in_synpred2_VirtualWorldModelingLanguage1752 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1754 = new BitSet(new long[]{0x0000000000000002L});

}