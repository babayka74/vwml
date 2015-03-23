// $ANTLR 3.4 C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g 2015-03-23 13:22:49

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

// compilation sink
import com.vw.lang.processor.model.sink.CompilationSink;

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
    		private com.vw.lang.sink.OperationInfo opInfo;
    		
    		public VWMLCodeGeneratorRecognitionException() {
    			super();
    		}
    		
    		public VWMLCodeGeneratorRecognitionException(com.vw.lang.sink.OperationInfo opInfo) {
    			initCause(new Throwable("compilation failed"));
    			this.opInfo = opInfo;
    		}
    		
    		public com.vw.lang.sink.OperationInfo getLastOperationInfo() {
    			return opInfo;
    		}
    	}

    	private VWMLModelBuilder vwmlModelBuilder = null;
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
     	private CompilationSink compilationSink = null;
     	
     	private String lastProcessedIAS = null;
     	
     	private Logger logger = Logger.getLogger(this.getClass());
    	
    	
    	@Override
    	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
    		String msg = super.getErrorMessage(e, tokenNames);
    		if (msg != null && msg.length() != 0) {
    			logger.error(getErrorHeader(e) + " " + msg);
    		}
    		CompilationSink csink = getCompilationSink();
    		if (csink != null) {
        			com.vw.lang.sink.OperationInfo opInfo = new com.vw.lang.sink.OperationInfo();
        			opInfo.setLine(e.line);
        			opInfo.setPosition(e.charPositionInLine);
        			opInfo.setFileName(getSourceName());
        			opInfo.setDescription(msg);
     			csink.delegateErrorCompilationMessage(opInfo);
    		}
    		return msg;
    	}
    	
    	public void setVwmlModelBuilder(VWMLModelBuilder vwmlModelBuilder) {
    		this.vwmlModelBuilder = vwmlModelBuilder;
    	}
    	
    	public void setCompilationSink(CompilationSink compilationSink) {
    		this.compilationSink = compilationSink;
    	}
    	
    	public CompilationSink getCompilationSink() {
    		return this.compilationSink;
    	}
    	
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

    	protected void startModule(String modName) throws RecognitionException {
        		if (logger.isInfoEnabled()) {
        			logger.info("Compiling module '" + modName + "'");
        		}
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
    	}
    	
    	protected void endModule() throws RecognitionException {
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
    		if (compilationSink != null) {
    			compilationSink.handleInclude(file);
    		}
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
        		com.vw.lang.sink.OperationInfo opInfo = new com.vw.lang.sink.OperationInfo();
        		org.antlr.runtime.Token token = getTokenStream().get(getTokenStream().index());
        		opInfo.setLine(token.getLine());
        		opInfo.setPosition(token.getCharPositionInLine());
        		opInfo.setFileName(getSourceName());
        		opInfo.setNextToken(token.getText());
    		throw new VWMLCodeGeneratorRecognitionException(opInfo);
    	}
    	
    	// DIRECTIVES
    	
    	protected boolean scanOnly() {
    		if (compilationSink != null && compilationSink.getMode() == CompilationSink.Mode.SCAN_ONLY) {
    			return true;
    		}
    		return false;
    	}
    	
    	protected boolean skipOff() throws RecognitionException {
    		try {
    			boolean b = !preprocessor.getResultOfProcessingDirectiveIf();
    			if (b && compilationSink != null) {
        				com.vw.lang.sink.OperationInfo opInfo = new com.vw.lang.sink.OperationInfo();
        				org.antlr.runtime.Token token = getTokenStream().get(getTokenStream().index());
        				opInfo.setLine(token.getLine());
        				opInfo.setPosition(token.getCharPositionInLine());
        				opInfo.setFileName(getSourceName());
        				opInfo.setNextToken(token.getText());
    				compilationSink.skippedCode(opInfo);
    			}
    			return b;
    		}
    		catch(Exception e) {
    			rethrowVWMLExceptionAsRecognitionException(e);
    		}
    		return false;
    	}
    	



    // $ANTLR start "filedef"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:824:1: filedef : ( props )? ( include ( include )* )? ( external )? ( module )? EOF ;
    public final void filedef() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:5: ( ( props )? ( include ( include )* )? ( external )? ( module )? EOF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:7: ( props )? ( include ( include )* )? ( external )? ( module )? EOF
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:7: ( props )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==94) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:7: props
                    {
                    pushFollow(FOLLOW_props_in_filedef673);
                    props();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:14: ( include ( include )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==91) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:15: include ( include )*
                    {
                    pushFollow(FOLLOW_include_in_filedef677);
                    include();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:23: ( include )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==91) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:24: include
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


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:36: ( external )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==89) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:36: external
                    {
                    pushFollow(FOLLOW_external_in_filedef686);
                    external();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:46: ( module )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==93) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:825:46: module
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
                                         	if (!scanOnly() && moduleInProgress && modProps != null) {
                                         		endModule();
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:832:1: external : 'external' '{' externalBody '}' ;
    public final void external() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:833:5: ( 'external' '{' externalBody '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:833:7: 'external' '{' externalBody '}'
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:836:1: externalBody : ( externalContexts )? ( externalEntities )? ;
    public final void externalBody() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:5: ( ( externalContexts )? ( externalEntities )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:7: ( externalContexts )? ( externalEntities )?
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:7: ( externalContexts )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==84) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:7: externalContexts
                    {
                    pushFollow(FOLLOW_externalContexts_in_externalBody736);
                    externalContexts();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:25: ( externalEntities )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==87) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:837:25: externalEntities
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:840:1: externalContexts : 'contexts' '{' ( externalContext )* '}' ;
    public final void externalContexts() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:841:5: ( 'contexts' '{' ( externalContext )* '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:841:7: 'contexts' '{' ( externalContext )* '}'
            {
            match(input,84,FOLLOW_84_in_externalContexts758); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_externalContexts760); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:841:22: ( externalContext )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==STRING_LITERAL) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:841:23: externalContext
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:844:1: externalEntities : 'entities' '{' ( externalEntity )* '}' ;
    public final void externalEntities() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:5: ( 'entities' '{' ( externalEntity )* '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:7: 'entities' '{' ( externalEntity )* '}'
            {
            match(input,87,FOLLOW_87_in_externalEntities784); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_externalEntities786); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:22: ( externalEntity )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==STRING_LITERAL) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:845:23: externalEntity
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:848:1: externalContext : string ;
    public final void externalContext() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string1 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:849:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:849:8: string
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:857:1: externalEntity : string ;
    public final void externalEntity() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string2 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:858:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:858:8: string
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:866:1: include : include_vwml ;
    public final void include() throws RecognitionException {
        String include_vwml3 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:867:5: ( include_vwml )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:867:7: include_vwml
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:875:1: include_vwml returns [String id] : 'include' STRING_LITERAL ;
    public final String include_vwml() throws RecognitionException {
        String id = null;


        Token STRING_LITERAL4=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:876:5: ( 'include' STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:876:8: 'include' STRING_LITERAL
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:879:1: props : 'options' '{' optionsList '}' ;
    public final void props() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:880:5: ( 'options' '{' optionsList '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:880:7: 'options' '{' optionsList '}'
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:883:1: optionsList : lang ( conflictring )? ;
    public final void optionsList() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:884:5: ( lang ( conflictring )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:884:7: lang ( conflictring )?
            {
            pushFollow(FOLLOW_lang_in_optionsList932);
            lang();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:884:12: ( conflictring )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==82) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:884:12: conflictring
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:887:1: lang : ( ( 'language' '=' JAVA )=> langJava | otherLanguages );
    public final void lang() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:888:5: ( ( 'language' '=' JAVA )=> langJava | otherLanguages )
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
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:888:7: ( 'language' '=' JAVA )=> langJava
                    {
                    pushFollow(FOLLOW_langJava_in_lang962);
                    langJava();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:889:7: otherLanguages
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:892:1: otherLanguages :;
    public final void otherLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:893:5: ()
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:894:5: 
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:896:1: langJava : 'language' '=' JAVA '{' javaProps '}' ;
    public final void langJava() throws RecognitionException {

               codeGenerator = vwmlModelBuilder.getCodeGenerator(VWMLModelBuilder.SINK_TYPE.JAVA);
               if (vwmlModelBuilder.getProjectProps() != null && vwmlModelBuilder.getProjectProps().getCodeGenerator() == null) {
               		vwmlModelBuilder.getProjectProps().setCodeGenerator(codeGenerator);
               }
               if (logger.isDebugEnabled()) {
               		logger.debug("Code generator '" + codeGenerator + "'");
               }
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:906:5: ( 'language' '=' JAVA '{' javaProps '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:906:7: 'language' '=' JAVA '{' javaProps '}'
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:909:1: javaProps : obligatoryProps optionalProps ;
    public final void javaProps() throws RecognitionException {

            	setupProps();
            
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:913:5: ( obligatoryProps optionalProps )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:913:7: obligatoryProps optionalProps
            {
            pushFollow(FOLLOW_obligatoryProps_in_javaProps1049);
            obligatoryProps();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_optionalProps_in_javaProps1051);
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



    // $ANTLR start "obligatoryProps"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:917:1: obligatoryProps : ( obligatoryProp )* ;
    public final void obligatoryProps() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:918:5: ( ( obligatoryProp )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:918:7: ( obligatoryProp )*
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:918:7: ( obligatoryProp )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==P_ELSE||LA12_0==P_IF||(LA12_0 >= 95 && LA12_0 <= 96)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:918:8: obligatoryProp
            	    {
            	    pushFollow(FOLLOW_obligatoryProp_in_obligatoryProps1070);
            	    obligatoryProp();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop12;
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
    // $ANTLR end "obligatoryProps"



    // $ANTLR start "obligatoryProp"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:921:1: obligatoryProp : ( propPackage | generatedFileLocation | pjavaPropsBlock | pswitch );
    public final void obligatoryProp() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:922:5: ( propPackage | generatedFileLocation | pjavaPropsBlock | pswitch )
            int alt13=4;
            switch ( input.LA(1) ) {
            case 95:
                {
                alt13=1;
                }
                break;
            case 96:
                {
                alt13=2;
                }
                break;
            case P_IF:
                {
                alt13=3;
                }
                break;
            case P_ELSE:
                {
                alt13=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:922:7: propPackage
                    {
                    pushFollow(FOLLOW_propPackage_in_obligatoryProp1089);
                    propPackage();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:923:7: generatedFileLocation
                    {
                    pushFollow(FOLLOW_generatedFileLocation_in_obligatoryProp1097);
                    generatedFileLocation();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:924:7: pjavaPropsBlock
                    {
                    pushFollow(FOLLOW_pjavaPropsBlock_in_obligatoryProp1105);
                    pjavaPropsBlock();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:925:7: pswitch
                    {
                    pushFollow(FOLLOW_pswitch_in_obligatoryProp1113);
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
    // $ANTLR end "obligatoryProp"



    // $ANTLR start "propPackage"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:929:1: propPackage : 'package' '=' packageName ;
    public final void propPackage() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return packageName5 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:930:5: ( 'package' '=' packageName )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:930:7: 'package' '=' packageName
            {
            match(input,95,FOLLOW_95_in_propPackage1135); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_propPackage1137); if (state.failed) return ;

            pushFollow(FOLLOW_packageName_in_propPackage1139);
            packageName5=packageName();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			  if (modProps != null && !skipOff()) {
            	    			  	String packageName = GeneralUtils.trimQuotes((packageName5!=null?input.toString(packageName5.start,packageName5.stop):null));
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setModulePackage(packageName);
            	    			  	if (compilationSink != null) {
            	    			  		compilationSink.publishModulePackage(packageName);
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
    // $ANTLR end "propPackage"


    public static class packageName_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "packageName"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:941:1: packageName : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.packageName_return packageName() throws RecognitionException {
        VirtualWorldModelingLanguageParser.packageName_return retval = new VirtualWorldModelingLanguageParser.packageName_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:942:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:942:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_packageName1158); if (state.failed) return retval;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:945:1: generatedFileLocation : 'path' '=' path ;
    public final void generatedFileLocation() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return path6 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:946:5: ( 'path' '=' path )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:946:7: 'path' '=' path
            {
            match(input,96,FOLLOW_96_in_generatedFileLocation1175); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_generatedFileLocation1177); if (state.failed) return ;

            pushFollow(FOLLOW_path_in_generatedFileLocation1179);
            path6=path();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (modProps != null && !skipOff()) {
                				String fileLocation = GeneralUtils.trimQuotes((path6!=null?input.toString(path6.start,path6.stop):null));
                				((JavaCodeGenerator.JavaModuleStartProps)modProps).setSrcPath(fileLocation);
             	    			if (compilationSink != null) {
            	    				compilationSink.publishFileLocation(fileLocation);
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
    // $ANTLR end "generatedFileLocation"



    // $ANTLR start "optionalProps"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:957:1: optionalProps : ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )? ;
    public final void optionalProps() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:5: ( ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )? )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:7: ( author )? ( projname )? ( description )? ( entity_history_size )? ( visualizer )? ( beyond_the_fringe )? ( conflictring )?
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:7: ( author )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==79) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:7: author
                    {
                    pushFollow(FOLLOW_author_in_optionalProps1199);
                    author();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:15: ( projname )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==97) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:15: projname
                    {
                    pushFollow(FOLLOW_projname_in_optionalProps1202);
                    projname();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:25: ( description )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==86) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:25: description
                    {
                    pushFollow(FOLLOW_description_in_optionalProps1205);
                    description();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:38: ( entity_history_size )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==88) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:38: entity_history_size
                    {
                    pushFollow(FOLLOW_entity_history_size_in_optionalProps1208);
                    entity_history_size();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:59: ( visualizer )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==99) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:59: visualizer
                    {
                    pushFollow(FOLLOW_visualizer_in_optionalProps1211);
                    visualizer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:71: ( beyond_the_fringe )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==80) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:71: beyond_the_fringe
                    {
                    pushFollow(FOLLOW_beyond_the_fringe_in_optionalProps1214);
                    beyond_the_fringe();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:90: ( conflictring )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==82) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:958:90: conflictring
                    {
                    pushFollow(FOLLOW_conflictring_in_optionalProps1217);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:961:1: author : 'author' '=' string ;
    public final void author() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string7 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:962:5: ( 'author' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:962:7: 'author' '=' string
            {
            match(input,79,FOLLOW_79_in_author1235); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_author1237); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_author1239);
            string7=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				String author = GeneralUtils.trimQuotes((string7!=null?input.toString(string7.start,string7.stop):null));
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setAuthor(author);
             	    				if (compilationSink != null) {
            	    					compilationSink.publishAuthor(author);
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
    // $ANTLR end "author"



    // $ANTLR start "projname"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:973:1: projname : 'project_name' '=' string ;
    public final void projname() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string8 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:974:5: ( 'project_name' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:974:7: 'project_name' '=' string
            {
            match(input,97,FOLLOW_97_in_projname1258); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_projname1260); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_projname1262);
            string8=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
            	    			if (modProps != null) {
            	    				String projectName = GeneralUtils.trimQuotes((string8!=null?input.toString(string8.start,string8.stop):null));
            	    				((JavaCodeGenerator.JavaModuleStartProps)modProps).setProjectName(projectName);
             	    				if (compilationSink != null) {
            	    					compilationSink.publishProjectName(projectName);
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
    // $ANTLR end "projname"



    // $ANTLR start "description"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:985:1: description : 'description' '=' string ;
    public final void description() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string9 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:986:5: ( 'description' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:986:7: 'description' '=' string
            {
            match(input,86,FOLLOW_86_in_description1285); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_description1287); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_description1289);
            string9=string();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                				if (modProps != null) {
                					String projectDescription = GeneralUtils.trimQuotes((string9!=null?input.toString(string9.start,string9.stop):null));
                					((JavaCodeGenerator.JavaModuleStartProps)modProps).setDescription(projectDescription);
             	    				if (compilationSink != null) {
            	    					compilationSink.publishProjectDescription(projectDescription);
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
    // $ANTLR end "description"



    // $ANTLR start "entity_history_size"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:997:1: entity_history_size : 'entity_history_size' '=' string ;
    public final void entity_history_size() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string10 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:998:5: ( 'entity_history_size' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:998:7: 'entity_history_size' '=' string
            {
            match(input,88,FOLLOW_88_in_entity_history_size1308); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_entity_history_size1310); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_entity_history_size1312);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1006:1: visualizer : 'visualizer' '{' visualizer_body '}' ;
    public final void visualizer() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1007:5: ( 'visualizer' '{' visualizer_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1007:7: 'visualizer' '{' visualizer_body '}'
            {
            match(input,99,FOLLOW_99_in_visualizer1332); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_visualizer1334); if (state.failed) return ;

            pushFollow(FOLLOW_visualizer_body_in_visualizer1336);
            visualizer_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,101,FOLLOW_101_in_visualizer1338); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1010:1: visualizer_body : ( visualizer_class visualizer_datapath |);
    public final void visualizer_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1011:5: ( visualizer_class visualizer_datapath |)
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==81) ) {
                alt21=1;
            }
            else if ( (LA21_0==101) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1011:7: visualizer_class visualizer_datapath
                    {
                    pushFollow(FOLLOW_visualizer_class_in_visualizer_body1356);
                    visualizer_class();

                    state._fsp--;
                    if (state.failed) return ;

                    pushFollow(FOLLOW_visualizer_datapath_in_visualizer_body1358);
                    visualizer_datapath();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1013:5: 
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1015:1: visualizer_class : 'class' '=' string ;
    public final void visualizer_class() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string11 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1016:5: ( 'class' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1016:7: 'class' '=' string
            {
            match(input,81,FOLLOW_81_in_visualizer_class1381); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_visualizer_class1383); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_class1385);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1023:1: visualizer_datapath : 'data' '=' string ;
    public final void visualizer_datapath() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string12 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1024:5: ( 'data' '=' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1024:7: 'data' '=' string
            {
            match(input,85,FOLLOW_85_in_visualizer_datapath1408); if (state.failed) return ;

            match(input,P_OP_E,FOLLOW_P_OP_E_in_visualizer_datapath1410); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_visualizer_datapath1412);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1031:1: path : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.path_return path() throws RecognitionException {
        VirtualWorldModelingLanguageParser.path_return retval = new VirtualWorldModelingLanguageParser.path_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1032:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1032:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_path1435); if (state.failed) return retval;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1036:1: beyond_the_fringe : 'beyond' '{' beyond_the_fringe_body '}' ;
    public final void beyond_the_fringe() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1037:5: ( 'beyond' '{' beyond_the_fringe_body '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1037:7: 'beyond' '{' beyond_the_fringe_body '}'
            {
            match(input,80,FOLLOW_80_in_beyond_the_fringe1453); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_beyond_the_fringe1455); if (state.failed) return ;

            pushFollow(FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe1457);
            beyond_the_fringe_body();

            state._fsp--;
            if (state.failed) return ;

            match(input,101,FOLLOW_101_in_beyond_the_fringe1459); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1040:1: beyond_the_fringe_body : finges ;
    public final void beyond_the_fringe_body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1041:5: ( finges )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1041:8: finges
            {
            pushFollow(FOLLOW_finges_in_beyond_the_fringe_body1481);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1044:1: finges : ( fringe )+ ;
    public final void finges() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1045:5: ( ( fringe )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1045:8: ( fringe )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1045:8: ( fringe )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==90) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1045:9: fringe
            	    {
            	    pushFollow(FOLLOW_fringe_in_finges1500);
            	    fringe();

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1048:1: fringe : 'fringe' ID 'ias' '(' creatures ')' ;
    public final void fringe() throws RecognitionException {
        Token ID13=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1049:5: ( 'fringe' ID 'ias' '(' creatures ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1049:8: 'fringe' ID 'ias' '(' creatures ')'
            {
            match(input,90,FOLLOW_90_in_fringe1520); if (state.failed) return ;

            ID13=(Token)match(input,ID,FOLLOW_ID_in_fringe1522); if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_fringe1524); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			setActiveFringe(ID13.getText());
                		}

            match(input,76,FOLLOW_76_in_fringe1550); if (state.failed) return ;

            pushFollow(FOLLOW_creatures_in_fringe1552);
            creatures();

            state._fsp--;
            if (state.failed) return ;

            match(input,77,FOLLOW_77_in_fringe1554); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1056:1: creatures : ( creatureblock )+ ;
    public final void creatures() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1057:5: ( ( creatureblock )+ )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1057:7: ( creatureblock )+
            {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1057:7: ( creatureblock )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==ID||LA23_0==P_ELSE||LA23_0==P_IF) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1057:8: creatureblock
            	    {
            	    pushFollow(FOLLOW_creatureblock_in_creatures1572);
            	    creatureblock();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
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
    // $ANTLR end "creatures"



    // $ANTLR start "creatureblock"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1060:1: creatureblock : ( creature | pfringedefblock | pswitch );
    public final void creatureblock() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1061:5: ( creature | pfringedefblock | pswitch )
            int alt24=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt24=1;
                }
                break;
            case P_IF:
                {
                alt24=2;
                }
                break;
            case P_ELSE:
                {
                alt24=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1061:7: creature
                    {
                    pushFollow(FOLLOW_creature_in_creatureblock1591);
                    creature();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1062:7: pfringedefblock
                    {
                    pushFollow(FOLLOW_pfringedefblock_in_creatureblock1599);
                    pfringedefblock();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1063:7: pswitch
                    {
                    pushFollow(FOLLOW_pswitch_in_creatureblock1607);
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
    // $ANTLR end "creatureblock"



    // $ANTLR start "creature"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1066:1: creature : ID 'ias' string ;
    public final void creature() throws RecognitionException {
        Token ID14=null;
        VirtualWorldModelingLanguageParser.string_return string15 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1078:5: ( ID 'ias' string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1078:7: ID 'ias' string
            {
            ID14=(Token)match(input,ID,FOLLOW_ID_in_creature1634); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff()) {
                				addLastDeclaredCreature(ID14.getText());
                			}
                	 	}

            match(input,IAS,FOLLOW_IAS_in_creature1639); if (state.failed) return ;

            pushFollow(FOLLOW_string_in_creature1641);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1090:1: conflictring : 'conflictring' '{' ( conflictdef )* '}' ;
    public final void conflictring() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1091:5: ( 'conflictring' '{' ( conflictdef )* '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1091:7: 'conflictring' '{' ( conflictdef )* '}'
            {
            match(input,82,FOLLOW_82_in_conflictring1668); if (state.failed) return ;

            match(input,100,FOLLOW_100_in_conflictring1670); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1091:26: ( conflictdef )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==STRING_LITERAL) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1091:26: conflictdef
            	    {
            	    pushFollow(FOLLOW_conflictdef_in_conflictring1672);
            	    conflictdef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            match(input,101,FOLLOW_101_in_conflictring1675); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1094:1: conflictdef : name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')' ;
    public final void conflictdef() throws RecognitionException {
        String name_of_conflict_on_ring16 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1095:5: ( name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1095:7: name_of_conflict_on_ring 'conflicts' '(' ( name_of_related_conflict_on_ring )+ ')'
            {
            pushFollow(FOLLOW_name_of_conflict_on_ring_in_conflictdef1693);
            name_of_conflict_on_ring16=name_of_conflict_on_ring();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { startConflictDefinitionOnRing(GeneralUtils.trimQuotes(name_of_conflict_on_ring16)); }

            match(input,83,FOLLOW_83_in_conflictdef1697); if (state.failed) return ;

            match(input,76,FOLLOW_76_in_conflictdef1699); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1095:138: ( name_of_related_conflict_on_ring )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==STRING_LITERAL) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1095:138: name_of_related_conflict_on_ring
            	    {
            	    pushFollow(FOLLOW_name_of_related_conflict_on_ring_in_conflictdef1701);
            	    name_of_related_conflict_on_ring();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
            } while (true);


            match(input,77,FOLLOW_77_in_conflictdef1704); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1098:1: name_of_conflict_on_ring returns [String id] : string ;
    public final String name_of_conflict_on_ring() throws RecognitionException {
        String id = null;


        VirtualWorldModelingLanguageParser.string_return string17 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1099:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1099:7: string
            {
            pushFollow(FOLLOW_string_in_name_of_conflict_on_ring1731);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1102:1: name_of_related_conflict_on_ring : string ;
    public final void name_of_related_conflict_on_ring() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return string18 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1103:5: ( string )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1103:7: string
            {
            pushFollow(FOLLOW_string_in_name_of_related_conflict_on_ring1752);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1106:1: module : 'module' ID body ;
    public final void module() throws RecognitionException {
        Token ID19=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1107:5: ( 'module' ID body )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1107:7: 'module' ID body
            {
            match(input,93,FOLLOW_93_in_module1771); if (state.failed) return ;

            ID19=(Token)match(input,ID,FOLLOW_ID_in_module1773); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (logger.isInfoEnabled()) {
                				logger.info("Scan mode '" + scanOnly() + "'");
                			}
                			if (!scanOnly()) {
                				startModule(ID19.getText());
                			}
                			else {
             	    			if (compilationSink != null) {
            	    				compilationSink.publishModuleName(ID19.getText());
            	    			}
                			}
                			// starts module's definition
                              }

            pushFollow(FOLLOW_body_in_module1777);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1123:1: body : '{' ( expression ( expression )* )? '}' ;
    public final void body() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1124:4: ( '{' ( expression ( expression )* )? '}' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1124:6: '{' ( expression ( expression )* )? '}'
            {
            match(input,100,FOLLOW_100_in_body1797); if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1124:10: ( expression ( expression )* )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==ID||LA28_0==LIFETERM||LA28_0==P_ELSE||LA28_0==P_IF||LA28_0==76||LA28_0==78||LA28_0==98) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1124:11: expression ( expression )*
                    {
                    pushFollow(FOLLOW_expression_in_body1800);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1124:22: ( expression )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==ID||LA27_0==LIFETERM||LA27_0==P_ELSE||LA27_0==P_IF||LA27_0==76||LA27_0==78||LA27_0==98) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1124:23: expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_body1803);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,101,FOLLOW_101_in_body1809); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1128:1: expression : ( ( bunch_of_entity_decls IAS )=> entity_def | check_term_def | pvwmlblock | pswitch );
    public final void expression() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1129:5: ( ( bunch_of_entity_decls IAS )=> entity_def | check_term_def | pvwmlblock | pswitch )
            int alt29=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA29_1 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt29=1;
                }
                else if ( (true) ) {
                    alt29=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 1, input);

                    throw nvae;

                }
                }
                break;
            case 76:
                {
                int LA29_2 = input.LA(2);

                if ( (synpred2_VirtualWorldModelingLanguage()) ) {
                    alt29=1;
                }
                else if ( (true) ) {
                    alt29=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 2, input);

                    throw nvae;

                }
                }
                break;
            case LIFETERM:
            case 78:
            case 98:
                {
                alt29=2;
                }
                break;
            case P_IF:
                {
                alt29=3;
                }
                break;
            case P_ELSE:
                {
                alt29=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }

            switch (alt29) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1129:7: ( bunch_of_entity_decls IAS )=> entity_def
                    {
                    pushFollow(FOLLOW_entity_def_in_expression1835);
                    entity_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1130:7: check_term_def
                    {
                    pushFollow(FOLLOW_check_term_def_in_expression1843);
                    check_term_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1131:7: pvwmlblock
                    {
                    pushFollow(FOLLOW_pvwmlblock_in_expression1851);
                    pvwmlblock();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1132:7: pswitch
                    {
                    pushFollow(FOLLOW_pswitch_in_expression1859);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1135:1: entity_def : bunch_of_entity_decls IAS ( term )* SEMICOLON ;
    public final void entity_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1136:5: ( bunch_of_entity_decls IAS ( term )* SEMICOLON )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1136:7: bunch_of_entity_decls IAS ( term )* SEMICOLON
            {
            pushFollow(FOLLOW_bunch_of_entity_decls_in_entity_def1876);
            bunch_of_entity_decls();

            state._fsp--;
            if (state.failed) return ;

            match(input,IAS,FOLLOW_IAS_in_entity_def1878); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
            	    			// adds entity id to context stack
            	    			declareAbsoluteContextByIASRelation();
                			}
                		}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1142:9: ( term )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==ID||LA30_0==LIFETERM||LA30_0==P_ELSE||LA30_0==P_IF||LA30_0==76||LA30_0==78||LA30_0==98) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1142:10: term
            	    {
            	    pushFollow(FOLLOW_term_in_entity_def1889);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entity_def1893); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                		      	if (!skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1152:1: check_term_def : ( ( source_lifetrerm )? LIFETERM '=' lifeterm_def | term_def );
    public final void check_term_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1153:5: ( ( source_lifetrerm )? LIFETERM '=' lifeterm_def | term_def )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LIFETERM||LA32_0==98) ) {
                alt32=1;
            }
            else if ( (LA32_0==ID||LA32_0==76||LA32_0==78) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1153:7: ( source_lifetrerm )? LIFETERM '=' lifeterm_def
                    {
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1153:7: ( source_lifetrerm )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==98) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1153:7: source_lifetrerm
                            {
                            pushFollow(FOLLOW_source_lifetrerm_in_check_term_def1927);
                            source_lifetrerm();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    match(input,LIFETERM,FOLLOW_LIFETERM_in_check_term_def1930); if (state.failed) return ;

                    match(input,P_OP_E,FOLLOW_P_OP_E_in_check_term_def1932); if (state.failed) return ;

                    pushFollow(FOLLOW_lifeterm_def_in_check_term_def1934);
                    lifeterm_def();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1154:7: term_def
                    {
                    pushFollow(FOLLOW_term_def_in_check_term_def1942);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1157:1: source_lifetrerm : 'source' ;
    public final void source_lifetrerm() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1158:5: ( 'source' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1158:7: 'source'
            {
            match(input,98,FOLLOW_98_in_source_lifetrerm1959); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1168:1: lifeterm_def : term_def ;
    public final void lifeterm_def() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1169:5: ( term_def )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1169:8: term_def
            {
            pushFollow(FOLLOW_term_def_in_lifeterm_def1979);
            term_def();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1190:1: term_def : entity ( oplist )* ;
    public final void term_def() throws RecognitionException {
        EntityWalker.Relation entity20 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1191:5: ( entity ( oplist )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1191:7: entity ( oplist )*
            {
            pushFollow(FOLLOW_entity_in_term_def1998);
            entity20=entity();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
                				lastProcessedEntity = entity20;
                				lastProcessedEntityAsTerm = false;
                				if (lastProcessedEntity != null && logger.isDebugEnabled()) {
                					logger.debug(">> '" + lastProcessedEntity.getObj() + "' <<");
                				}
                			}
                	     	}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1199:14: ( oplist )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( ((LA33_0 >= OPACTIVATE && LA33_0 <= OPSUBSTRUCT)) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1199:15: oplist
            	    {
            	    pushFollow(FOLLOW_oplist_in_term_def2004);
            	    oplist();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            if ( state.backtracking==0 ) {  
              	       		if (lastProcessedEntityAsTerm && codeGenerator != null && !skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1219:1: entity_decl : ( simple_entity_decl | complex_entity_decl );
    public final void entity_decl() throws RecognitionException {
        String simple_entity_decl21 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1220:5: ( simple_entity_decl | complex_entity_decl )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==ID) ) {
                alt34=1;
            }
            else if ( (LA34_0==76) ) {
                alt34=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;

            }
            switch (alt34) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1220:7: simple_entity_decl
                    {
                    pushFollow(FOLLOW_simple_entity_decl_in_entity_decl2035);
                    simple_entity_decl21=simple_entity_decl();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			if (!skipOff() && !complexEntityNameBuilderDecl.isInProgress() && !scanOnly()) {
                        				lastProcessedContextBunch.add(ContextBunchElement.build(simple_entity_decl21));
                        				if (logger.isDebugEnabled()) {
                        					logger.debug("+++++++++++++++++++++++ " + simple_entity_decl21);
                        				}
                        			}
                        		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1229:7: complex_entity_decl
                    {
                    pushFollow(FOLLOW_complex_entity_decl_in_entity_decl2051);
                    complex_entity_decl();

                    state._fsp--;
                    if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			if (!skipOff() && !scanOnly() && complexEntityNameBuilderDecl.isRootEntityFinishedProgress()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1238:1: bunch_of_entity_decls : entity_decl ( COMMA entity_decl )* ;
    public final void bunch_of_entity_decls() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1248:5: ( entity_decl ( COMMA entity_decl )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1249:7: entity_decl ( COMMA entity_decl )*
            {
            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
                				lastProcessedContextBunch = VWMLContextBuilder.ContextBunch.instance();
                				if (logger.isDebugEnabled()) {
                					logger.debug("Created bunch");
                				}
                			}
                		}

            pushFollow(FOLLOW_entity_decl_in_bunch_of_entity_decls2095);
            entity_decl();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1256:21: ( COMMA entity_decl )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1256:22: COMMA entity_decl
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_bunch_of_entity_decls2098); if (state.failed) return ;

            	    pushFollow(FOLLOW_entity_decl_in_bunch_of_entity_decls2100);
            	    entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1260:1: simple_entity_decl returns [String id] : ID ;
    public final String simple_entity_decl() throws RecognitionException {
        String id = null;


        Token ID22=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1261:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1261:7: ID
            {
            ID22=(Token)match(input,ID,FOLLOW_ID_in_simple_entity_decl2129); if (state.failed) return id;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1268:1: complex_entity_decl : '(' ( entity_decl )* ')' ;
    public final void complex_entity_decl() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1269:5: ( '(' ( entity_decl )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1269:7: '(' ( entity_decl )* ')'
            {
            match(input,76,FOLLOW_76_in_complex_entity_decl2153); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
                				complexEntityDeclarationPhase1();
                			}
                		}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1273:9: ( entity_decl )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==ID||LA36_0==76) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1273:10: entity_decl
            	    {
            	    pushFollow(FOLLOW_entity_decl_in_complex_entity_decl2159);
            	    entity_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
                				complexEntityDeclarationPhase2();
                			}
                		}

            match(input,77,FOLLOW_77_in_complex_entity_decl2176); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1282:1: term : expression ;
    public final void term() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1283:5: ( expression )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1283:7: expression
            {
            pushFollow(FOLLOW_expression_in_term2193);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1286:1: entity returns [EntityWalker.Relation rel] : ( simple_entity | complex_entity | '.' );
    public final EntityWalker.Relation entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        EntityWalker.Relation simple_entity23 =null;

        EntityWalker.Relation complex_entity24 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1287:5: ( simple_entity | complex_entity | '.' )
            int alt37=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt37=1;
                }
                break;
            case 76:
                {
                alt37=2;
                }
                break;
            case 78:
                {
                alt37=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return rel;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }

            switch (alt37) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1287:7: simple_entity
                    {
                    pushFollow(FOLLOW_simple_entity_in_entity2216);
                    simple_entity23=simple_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) { 
                        			rel = simple_entity23;
                        		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1292:7: complex_entity
                    {
                    pushFollow(FOLLOW_complex_entity_in_entity2233);
                    complex_entity24=complex_entity();

                    state._fsp--;
                    if (state.failed) return rel;

                    if ( state.backtracking==0 ) { 
                        			rel = complex_entity24;
                        		}

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1296:7: '.'
                    {
                    match(input,78,FOLLOW_78_in_entity2249); if (state.failed) return rel;

                    if ( state.backtracking==0 ) {
                                    	if (!skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1304:1: simple_entity returns [EntityWalker.Relation rel] : ID ;
    public final EntityWalker.Relation simple_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;


        Token ID25=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1305:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1305:7: ID
            {
            ID25=(Token)match(input,ID,FOLLOW_ID_in_simple_entity2279); if (state.failed) return rel;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1314:1: complex_entity returns [EntityWalker.Relation rel] : '(' ( term )* ')' ;
    public final EntityWalker.Relation complex_entity() throws RecognitionException {
        EntityWalker.Relation rel = null;



            			if (!skipOff() && !scanOnly()) {
            				complexEntityStartAssembling();
            			}
            		
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1325:5: ( '(' ( term )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1325:7: '(' ( term )* ')'
            {
            match(input,76,FOLLOW_76_in_complex_entity2323); if (state.failed) return rel;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1325:11: ( term )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==ID||LA38_0==LIFETERM||LA38_0==P_ELSE||LA38_0==P_IF||LA38_0==76||LA38_0==78||LA38_0==98) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1325:12: term
            	    {
            	    pushFollow(FOLLOW_term_in_complex_entity2326);
            	    term();

            	    state._fsp--;
            	    if (state.failed) return rel;

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            match(input,77,FOLLOW_77_in_complex_entity2330); if (state.failed) return rel;

            }

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly()) {
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1338:1: oplist : opclist ;
    public final void oplist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return opclist26 =null;


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1340:5: ( opclist )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1340:7: opclist
            {
            pushFollow(FOLLOW_opclist_in_oplist2415);
            opclist26=opclist();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			if (!skipOff() && !scanOnly() && lastProcessedEntity != null && codeGenerator != null) { 
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1358:1: opclist : ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM | OPACTIVATECTX | OPACTIVATEONFRINGE | OPRELAX | OPSTARTCONFLICTGROUP | OPENDCONFLICTGROUP | OPBREAKPOINT | OPAPPLYTOCONTEXT | OPCLONE | OPBORN | OPPROJECTION | OPFOREACH | OPDYNCONTEXT | OPSIZE | OPINTERRUPT | OPCALLP | OPGET | OPFIND | OPGATE | OPRECALL | OPREPEAT | OPACTIVATE | OPCOPY | OPSTARTINTERCEPTION | OPFINISHINTERCEPTION | OPSTARTINTERCEPTION_S | OPFINISHINTERCEPTION_S | OPRELEASE | OPEXISTSI | OPEXISTSI_S | OPLTT );
    public final VirtualWorldModelingLanguageParser.opclist_return opclist() throws RecognitionException {
        VirtualWorldModelingLanguageParser.opclist_return retval = new VirtualWorldModelingLanguageParser.opclist_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1359:5: ( OPJOIN | OPINTERSECT | OPSUBSTRUCT | OPFIRST | OPLAST | OPBEGIN | OPREST | OPCARTESIAN | OPIN | OPINCL | OPEQ | OPIDENT | OPSQU | OPINTERPRET | OPCREATEEXPR | OPEXECUTE | OPRANDOM | OPACTIVATECTX | OPACTIVATEONFRINGE | OPRELAX | OPSTARTCONFLICTGROUP | OPENDCONFLICTGROUP | OPBREAKPOINT | OPAPPLYTOCONTEXT | OPCLONE | OPBORN | OPPROJECTION | OPFOREACH | OPDYNCONTEXT | OPSIZE | OPINTERRUPT | OPCALLP | OPGET | OPFIND | OPGATE | OPRECALL | OPREPEAT | OPACTIVATE | OPCOPY | OPSTARTINTERCEPTION | OPFINISHINTERCEPTION | OPSTARTINTERCEPTION_S | OPFINISHINTERCEPTION_S | OPRELEASE | OPEXISTSI | OPEXISTSI_S | OPLTT )
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1408:1: termLanguages : ( JAVA | C | CPP | OBJECTIVEC );
    public final void termLanguages() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1409:5: ( JAVA | C | CPP | OBJECTIVEC )
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1415:1: string : STRING_LITERAL ;
    public final VirtualWorldModelingLanguageParser.string_return string() throws RecognitionException {
        VirtualWorldModelingLanguageParser.string_return retval = new VirtualWorldModelingLanguageParser.string_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1416:5: ( STRING_LITERAL )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1416:7: STRING_LITERAL
            {
            match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string2870); if (state.failed) return retval;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1421:1: pfringedefblock : pstart pexpressions creatures pend ;
    public final void pfringedefblock() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1422:5: ( pstart pexpressions creatures pend )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1422:7: pstart pexpressions creatures pend
            {
            pushFollow(FOLLOW_pstart_in_pfringedefblock2889);
            pstart();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_pexpressions_in_pfringedefblock2891);
            pexpressions();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.processDirectiveIf();
                		}

            pushFollow(FOLLOW_creatures_in_pfringedefblock2908);
            creatures();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_pend_in_pfringedefblock2916);
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



    // $ANTLR start "pjavaPropsBlock"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1430:1: pjavaPropsBlock : pstart pexpressions javaProps pend ;
    public final void pjavaPropsBlock() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1431:5: ( pstart pexpressions javaProps pend )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1431:7: pstart pexpressions javaProps pend
            {
            pushFollow(FOLLOW_pstart_in_pjavaPropsBlock2933);
            pstart();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_pexpressions_in_pjavaPropsBlock2935);
            pexpressions();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.processDirectiveIf();
                		}

            pushFollow(FOLLOW_javaProps_in_pjavaPropsBlock2952);
            javaProps();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_pend_in_pjavaPropsBlock2960);
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
    // $ANTLR end "pjavaPropsBlock"



    // $ANTLR start "pvwmlblock"
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1440:1: pvwmlblock : pstart pexpressions ( expression )* pend ;
    public final void pvwmlblock() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1441:5: ( pstart pexpressions ( expression )* pend )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1441:7: pstart pexpressions ( expression )* pend
            {
            pushFollow(FOLLOW_pstart_in_pvwmlblock2978);
            pstart();

            state._fsp--;
            if (state.failed) return ;

            pushFollow(FOLLOW_pexpressions_in_pvwmlblock2980);
            pexpressions();

            state._fsp--;
            if (state.failed) return ;

            if ( state.backtracking==0 ) { 
                			preprocessor.processDirectiveIf();
                		}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1445:7: ( expression )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==ID||LA39_0==LIFETERM||LA39_0==P_ELSE||LA39_0==P_IF||LA39_0==76||LA39_0==78||LA39_0==98) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1445:8: expression
            	    {
            	    pushFollow(FOLLOW_expression_in_pvwmlblock2999);
            	    expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            pushFollow(FOLLOW_pend_in_pvwmlblock3009);
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1449:1: pexpressions : '(' ( pexpression )* ')' ;
    public final void pexpressions() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1450:5: ( '(' ( pexpression )* ')' )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1450:7: '(' ( pexpression )* ')'
            {
            match(input,76,FOLLOW_76_in_pexpressions3026); if (state.failed) return ;

            if ( state.backtracking==0 ) {
                			preprocessor.getTopDirectiveIf().addExpressionItem();
                		}

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1453:6: ( pexpression )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==ID) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1453:7: pexpression
            	    {
            	    pushFollow(FOLLOW_pexpression_in_pexpressions3037);
            	    pexpression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            match(input,77,FOLLOW_77_in_pexpressions3048); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1459:1: pstart : P_IF ;
    public final void pstart() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1460:5: ( P_IF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1460:7: P_IF
            {
            match(input,P_IF,FOLLOW_P_IF_in_pstart3067); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1465:1: pend : P_ENDIF ;
    public final void pend() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1466:5: ( P_ENDIF )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1466:7: P_ENDIF
            {
            match(input,P_ENDIF,FOLLOW_P_ENDIF_in_pend3087); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1472:1: pswitch : P_ELSE ;
    public final void pswitch() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1473:5: ( P_ELSE )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1473:7: P_ELSE
            {
            match(input,P_ELSE,FOLLOW_P_ELSE_in_pswitch3113); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1478:1: pexpression : pitem ( poperation ( pexpressions )* )* ;
    public final void pexpression() throws RecognitionException {
        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1479:5: ( pitem ( poperation ( pexpressions )* )* )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1479:7: pitem ( poperation ( pexpressions )* )*
            {
            pushFollow(FOLLOW_pitem_in_pexpression3134);
            pitem();

            state._fsp--;
            if (state.failed) return ;

            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1479:13: ( poperation ( pexpressions )* )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0 >= P_OP_AND && LA42_0 <= P_OP_OR)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1479:14: poperation ( pexpressions )*
            	    {
            	    pushFollow(FOLLOW_poperation_in_pexpression3137);
            	    poperation();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1479:25: ( pexpressions )*
            	    loop41:
            	    do {
            	        int alt41=2;
            	        int LA41_0 = input.LA(1);

            	        if ( (LA41_0==76) ) {
            	            alt41=1;
            	        }


            	        switch (alt41) {
            	    	case 1 :
            	    	    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1479:26: pexpressions
            	    	    {
            	    	    pushFollow(FOLLOW_pexpressions_in_pexpression3140);
            	    	    pexpressions();

            	    	    state._fsp--;
            	    	    if (state.failed) return ;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop41;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop42;
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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1482:1: pitem : ID ;
    public final void pitem() throws RecognitionException {
        Token ID27=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1483:5: ( ID )
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1483:7: ID
            {
            ID27=(Token)match(input,ID,FOLLOW_ID_in_pitem3165); if (state.failed) return ;

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
    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1488:1: poperation : ( P_OP_AND | P_OP_OR | P_OP_B | P_OP_L | P_OP_E );
    public final void poperation() throws RecognitionException {
        Token P_OP_AND28=null;
        Token P_OP_OR29=null;
        Token P_OP_B30=null;
        Token P_OP_L31=null;
        Token P_OP_E32=null;

        try {
            // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1489:5: ( P_OP_AND | P_OP_OR | P_OP_B | P_OP_L | P_OP_E )
            int alt43=5;
            switch ( input.LA(1) ) {
            case P_OP_AND:
                {
                alt43=1;
                }
                break;
            case P_OP_OR:
                {
                alt43=2;
                }
                break;
            case P_OP_B:
                {
                alt43=3;
                }
                break;
            case P_OP_L:
                {
                alt43=4;
                }
                break;
            case P_OP_E:
                {
                alt43=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;

            }

            switch (alt43) {
                case 1 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1489:7: P_OP_AND
                    {
                    P_OP_AND28=(Token)match(input,P_OP_AND,FOLLOW_P_OP_AND_in_poperation3184); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_AND28!=null?P_OP_AND28.getText():null));
                        		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1492:7: P_OP_OR
                    {
                    P_OP_OR29=(Token)match(input,P_OP_OR,FOLLOW_P_OP_OR_in_poperation3194); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_OR29!=null?P_OP_OR29.getText():null));
                        		}

                    }
                    break;
                case 3 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1495:7: P_OP_B
                    {
                    P_OP_B30=(Token)match(input,P_OP_B,FOLLOW_P_OP_B_in_poperation3204); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_B30!=null?P_OP_B30.getText():null));
                        		}

                    }
                    break;
                case 4 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1498:7: P_OP_L
                    {
                    P_OP_L31=(Token)match(input,P_OP_L,FOLLOW_P_OP_L_in_poperation3214); if (state.failed) return ;

                    if ( state.backtracking==0 ) {
                        			preprocessor.getTopDirectiveIf().addOperation((P_OP_L31!=null?P_OP_L31.getText():null));
                        		}

                    }
                    break;
                case 5 :
                    // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1501:7: P_OP_E
                    {
                    P_OP_E32=(Token)match(input,P_OP_E,FOLLOW_P_OP_E_in_poperation3224); if (state.failed) return ;

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
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:888:7: ( 'language' '=' JAVA )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:888:8: 'language' '=' JAVA
        {
        match(input,92,FOLLOW_92_in_synpred1_VirtualWorldModelingLanguage953); if (state.failed) return ;

        match(input,P_OP_E,FOLLOW_P_OP_E_in_synpred1_VirtualWorldModelingLanguage955); if (state.failed) return ;

        match(input,JAVA,FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage957); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_VirtualWorldModelingLanguage

    // $ANTLR start synpred2_VirtualWorldModelingLanguage
    public final void synpred2_VirtualWorldModelingLanguage_fragment() throws RecognitionException {
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1129:7: ( bunch_of_entity_decls IAS )
        // C:\\Users\\Oleg\\projects\\vwml\\model\\src\\com\\vw\\lang\\grammar\\VirtualWorldModelingLanguage.g:1129:8: bunch_of_entity_decls IAS
        {
        pushFollow(FOLLOW_bunch_of_entity_decls_in_synpred2_VirtualWorldModelingLanguage1828);
        bunch_of_entity_decls();

        state._fsp--;
        if (state.failed) return ;

        match(input,IAS,FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1830); if (state.failed) return ;

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
    public static final BitSet FOLLOW_100_in_langJava1019 = new BitSet(new long[]{0x0000000000000000L,0x0000000B8145800AL});
    public static final BitSet FOLLOW_javaProps_in_langJava1021 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_langJava1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_obligatoryProps_in_javaProps1049 = new BitSet(new long[]{0x0000000000000000L,0x0000000A01458000L});
    public static final BitSet FOLLOW_optionalProps_in_javaProps1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_obligatoryProp_in_obligatoryProps1070 = new BitSet(new long[]{0x0000000000000002L,0x000000018000000AL});
    public static final BitSet FOLLOW_propPackage_in_obligatoryProp1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generatedFileLocation_in_obligatoryProp1097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pjavaPropsBlock_in_obligatoryProp1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pswitch_in_obligatoryProp1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_propPackage1135 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_propPackage1137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_packageName_in_propPackage1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_packageName1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_generatedFileLocation1175 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_generatedFileLocation1177 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_path_in_generatedFileLocation1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_author_in_optionalProps1199 = new BitSet(new long[]{0x0000000000000002L,0x0000000A01450000L});
    public static final BitSet FOLLOW_projname_in_optionalProps1202 = new BitSet(new long[]{0x0000000000000002L,0x0000000801450000L});
    public static final BitSet FOLLOW_description_in_optionalProps1205 = new BitSet(new long[]{0x0000000000000002L,0x0000000801050000L});
    public static final BitSet FOLLOW_entity_history_size_in_optionalProps1208 = new BitSet(new long[]{0x0000000000000002L,0x0000000800050000L});
    public static final BitSet FOLLOW_visualizer_in_optionalProps1211 = new BitSet(new long[]{0x0000000000000002L,0x0000000000050000L});
    public static final BitSet FOLLOW_beyond_the_fringe_in_optionalProps1214 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_conflictring_in_optionalProps1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_author1235 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_author1237 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_author1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_projname1258 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_projname1260 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_projname1262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_description1285 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_description1287 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_description1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_entity_history_size1308 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_entity_history_size1310 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_entity_history_size1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_visualizer1332 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_visualizer1334 = new BitSet(new long[]{0x0000000000000000L,0x0000002000020000L});
    public static final BitSet FOLLOW_visualizer_body_in_visualizer1336 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_visualizer1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_visualizer_class_in_visualizer_body1356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_visualizer_datapath_in_visualizer_body1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_visualizer_class1381 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_visualizer_class1383 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_visualizer_class1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_visualizer_datapath1408 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_visualizer_datapath1410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_visualizer_datapath1412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_path1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_beyond_the_fringe1453 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_beyond_the_fringe1455 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_beyond_the_fringe_body_in_beyond_the_fringe1457 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_beyond_the_fringe1459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finges_in_beyond_the_fringe_body1481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fringe_in_finges1500 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_fringe1520 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_fringe1522 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_fringe1524 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_fringe1550 = new BitSet(new long[]{0x0000000000000400L,0x000000000000000AL});
    public static final BitSet FOLLOW_creatures_in_fringe1552 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_fringe1554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_creatureblock_in_creatures1572 = new BitSet(new long[]{0x0000000000000402L,0x000000000000000AL});
    public static final BitSet FOLLOW_creature_in_creatureblock1591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pfringedefblock_in_creatureblock1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pswitch_in_creatureblock1607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_creature1634 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_creature1639 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_string_in_creature1641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_conflictring1668 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_conflictring1670 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000400L});
    public static final BitSet FOLLOW_conflictdef_in_conflictring1672 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000400L});
    public static final BitSet FOLLOW_101_in_conflictring1675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_of_conflict_on_ring_in_conflictdef1693 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_conflictdef1697 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_conflictdef1699 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_name_of_related_conflict_on_ring_in_conflictdef1701 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002400L});
    public static final BitSet FOLLOW_77_in_conflictdef1704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_name_of_conflict_on_ring1731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_name_of_related_conflict_on_ring1752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_module1771 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_ID_in_module1773 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_body_in_module1777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_body1797 = new BitSet(new long[]{0x0000000000002400L,0x000000240000500AL});
    public static final BitSet FOLLOW_expression_in_body1800 = new BitSet(new long[]{0x0000000000002400L,0x000000240000500AL});
    public static final BitSet FOLLOW_expression_in_body1803 = new BitSet(new long[]{0x0000000000002400L,0x000000240000500AL});
    public static final BitSet FOLLOW_101_in_body1809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_def_in_expression1835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_term_def_in_expression1843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pvwmlblock_in_expression1851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pswitch_in_expression1859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bunch_of_entity_decls_in_entity_def1876 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_entity_def1878 = new BitSet(new long[]{0x0000000000002400L,0x000000040000520AL});
    public static final BitSet FOLLOW_term_in_entity_def1889 = new BitSet(new long[]{0x0000000000002400L,0x000000040000520AL});
    public static final BitSet FOLLOW_SEMICOLON_in_entity_def1893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_source_lifetrerm_in_check_term_def1927 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_LIFETERM_in_check_term_def1930 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_check_term_def1932 = new BitSet(new long[]{0x0000000000000400L,0x0000000000005000L});
    public static final BitSet FOLLOW_lifeterm_def_in_check_term_def1934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_check_term_def1942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_source_lifetrerm1959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_def_in_lifeterm_def1979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_in_term_def1998 = new BitSet(new long[]{0xFFFFFFFFFFFE0002L});
    public static final BitSet FOLLOW_oplist_in_term_def2004 = new BitSet(new long[]{0xFFFFFFFFFFFE0002L});
    public static final BitSet FOLLOW_simple_entity_decl_in_entity_decl2035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_decl_in_entity_decl2051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entity_decl_in_bunch_of_entity_decls2095 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_bunch_of_entity_decls2098 = new BitSet(new long[]{0x0000000000000400L,0x0000000000001000L});
    public static final BitSet FOLLOW_entity_decl_in_bunch_of_entity_decls2100 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ID_in_simple_entity_decl2129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_complex_entity_decl2153 = new BitSet(new long[]{0x0000000000000400L,0x0000000000003000L});
    public static final BitSet FOLLOW_entity_decl_in_complex_entity_decl2159 = new BitSet(new long[]{0x0000000000000400L,0x0000000000003000L});
    public static final BitSet FOLLOW_77_in_complex_entity_decl2176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_term2193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_entity_in_entity2216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complex_entity_in_entity2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_entity2249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_entity2279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_complex_entity2323 = new BitSet(new long[]{0x0000000000002400L,0x000000040000700AL});
    public static final BitSet FOLLOW_term_in_complex_entity2326 = new BitSet(new long[]{0x0000000000002400L,0x000000040000700AL});
    public static final BitSet FOLLOW_77_in_complex_entity2330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_opclist_in_oplist2415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string2870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pstart_in_pfringedefblock2889 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_pexpressions_in_pfringedefblock2891 = new BitSet(new long[]{0x0000000000000400L,0x000000000000000AL});
    public static final BitSet FOLLOW_creatures_in_pfringedefblock2908 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_pend_in_pfringedefblock2916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pstart_in_pjavaPropsBlock2933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_pexpressions_in_pjavaPropsBlock2935 = new BitSet(new long[]{0x0000000000000000L,0x0000000B8145800AL});
    public static final BitSet FOLLOW_javaProps_in_pjavaPropsBlock2952 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_pend_in_pjavaPropsBlock2960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pstart_in_pvwmlblock2978 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_pexpressions_in_pvwmlblock2980 = new BitSet(new long[]{0x0000000000002400L,0x000000040000500EL});
    public static final BitSet FOLLOW_expression_in_pvwmlblock2999 = new BitSet(new long[]{0x0000000000002400L,0x000000040000500EL});
    public static final BitSet FOLLOW_pend_in_pvwmlblock3009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_pexpressions3026 = new BitSet(new long[]{0x0000000000000400L,0x0000000000002000L});
    public static final BitSet FOLLOW_pexpression_in_pexpressions3037 = new BitSet(new long[]{0x0000000000000400L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_pexpressions3048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_IF_in_pstart3067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_ENDIF_in_pend3087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_ELSE_in_pswitch3113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pitem_in_pexpression3134 = new BitSet(new long[]{0x0000000000000002L,0x00000000000001F0L});
    public static final BitSet FOLLOW_poperation_in_pexpression3137 = new BitSet(new long[]{0x0000000000000002L,0x00000000000011F0L});
    public static final BitSet FOLLOW_pexpressions_in_pexpression3140 = new BitSet(new long[]{0x0000000000000002L,0x00000000000011F0L});
    public static final BitSet FOLLOW_ID_in_pitem3165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_AND_in_poperation3184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_OR_in_poperation3194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_B_in_poperation3204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_L_in_poperation3214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_OP_E_in_poperation3224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_synpred1_VirtualWorldModelingLanguage953 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_P_OP_E_in_synpred1_VirtualWorldModelingLanguage955 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_JAVA_in_synpred1_VirtualWorldModelingLanguage957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bunch_of_entity_decls_in_synpred2_VirtualWorldModelingLanguage1828 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_IAS_in_synpred2_VirtualWorldModelingLanguage1830 = new BitSet(new long[]{0x0000000000000002L});

}