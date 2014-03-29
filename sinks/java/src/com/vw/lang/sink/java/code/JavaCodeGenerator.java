package com.vw.lang.sink.java.code;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.vw.lang.sink.ICodeGenerator;
import com.vw.lang.sink.java.VWMLJavaExportUtils;
import com.vw.lang.sink.java.VWMLObjectBuilder;
import com.vw.lang.sink.java.code.linkage.JavaCodeGeneratorLinkage;
import com.vw.lang.sink.java.code.module.JavaCodeGeneratorModule;
import com.vw.lang.sink.java.code.repository.JavaCodeGeneratorRepository;
import com.vw.lang.sink.java.code.utils.JavaCodeGeneratorUtils;
import com.vw.lang.sink.java.link.AbstractVWMLLinkVisitor;
import com.vw.lang.sink.utils.EntityWalker;
import com.vw.lang.sink.utils.EntityWalker.REL;
import com.vw.lang.sink.utils.GeneralUtils;

/**
 * Used by VWMLProcessor for code generation
 * @author ogibayev
 *
 */
public class JavaCodeGenerator implements ICodeGenerator {
	
	public static class JavaModuleStartProps extends StartModuleProps {
		private String srcPath;
		private String commonPackage;
		private String moduleName;
		private String modulePackage;
		private String author;
		private String projectName;
		private String description;
		private String entityHistorySize;
		private String date;
		private AbstractVWMLLinkVisitor visitor;
		private String visitorDataPath;
		
		public JavaModuleStartProps() {
			super();
		}

		public JavaModuleStartProps(String srcPath, String commonPackage,
				String moduleName, String modulePackage, String author, String projectName,
				String description, String entityHistorySize, String date, AbstractVWMLLinkVisitor visitor,
				String visitorDataPath) {
			super();
			this.srcPath = srcPath;
			this.commonPackage = commonPackage;
			this.moduleName = moduleName;
			this.modulePackage = modulePackage;
			this.author = author;
			this.projectName = projectName;
			this.description = description;
			this.entityHistorySize = entityHistorySize;
			this.date = date;
			this.visitor = visitor;
			this.visitorDataPath = visitorDataPath;
		}

		public String getSrcPath() {
			return srcPath;
		}

		public void setSrcPath(String srcPath) {
			this.srcPath = srcPath;
		}

		public String getCommonPackage() {
			return commonPackage;
		}

		public void setCommonPackage(String commonPackage) {
			this.commonPackage = commonPackage;
		}

		public String getModuleName() {
			return moduleName;
		}

		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}

		public String getModulePackage() {
			return modulePackage;
		}

		public void setModulePackage(String modulePackage) {
			this.modulePackage = modulePackage;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public AbstractVWMLLinkVisitor getVisitor() {
			return visitor;
		}

		public void setVisitor(AbstractVWMLLinkVisitor visitor) {
			this.visitor = visitor;
		}

		public String getVisitorDataPath() {
			return visitorDataPath;
		}

		public void setVisitorDataPath(String visitorDataPath) {
			this.visitorDataPath = visitorDataPath;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public String getEntityHistorySize() {
			return entityHistorySize;
		}

		public void setEntityHistorySize(String entityHistorySize) {
			this.entityHistorySize = entityHistorySize;
		}

		@Override
		public String toString() {
			return "JavaModuleStartProps [srcPath=" + srcPath
					+ ", commonPackage=" + commonPackage + ", moduleName="
					+ moduleName + ", modulePackage=" + modulePackage
					+ ", author=" + author + ", projectName=" + projectName
					+ ", description=" + description + ", entityHistorySize="
					+ entityHistorySize + ", date=" + date + ", visitor="
					+ visitor + ", visitorDataPath=" + visitorDataPath + "]";
		}
	}
	
	public static enum ModuleFiles {
		MODULE("Module"),
		REPOSITORY("Repository"),
		LINKAGE("Linkage");
		
		private final String value;
		
		ModuleFiles(String value) {
			this.value = value;
		}
		
		public static ModuleFiles fromValue(String value) {
			if (value != null) {
				for(ModuleFiles m : values()) {
					if (m.value.equals(value)) {
						return m;
					}
				}
			}
			return getDefault();
		}
		
		public String toValue() {
			return value;
		}
		
		public static ModuleFiles getDefault() {
			return MODULE;
		}
		
		public static int numValues() {
			return values().length;
		}
		
		public static int index(String value) {
			int i = 0;
			if (value != null) {
				for(ModuleFiles m : values()) {
					if (m.value.equals(value)) {
						return i;
					}
					i++;
				}
			}
			return i;
		}
		
		public static ModuleFiles index(int value) {
			ModuleFiles m = ModuleFiles.MODULE;
			if (value < numValues()) {
				m = ModuleFiles.values()[value];
			}
			return m;
		}
	};
	
	/**
	 * Simple wrapper for VWMLObject class; used during code generation
	 * @author ogibayev
	 *
	 */
	public static class VWMLObjWrap {
		private VWMLObjectBuilder.VWMLObjectType type;
		private Object objId;
		private Object readableId;
		private String context;

		public VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType type, Object objId, String context) {
			super();
			this.type = type;
			this.objId = objId;
			this.context = context;
		}

		public VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType type, Object objId, Object readableId, String context) {
			super();
			this.type = type;
			this.objId = objId;
			this.readableId = readableId;
			this.context = context;
		}
	
		public Object getObjId() {
			return objId;
		}

		public void setObjId(Object objId) {
			this.objId = objId;
		}
	
		public VWMLObjectBuilder.VWMLObjectType getType() {
			return type;
		}

		public void setReadableId(Object readableId) {
			this.readableId = readableId;
		}

		public Object getReadableId() {
			return readableId;
		}

		public String getContext() {
			return context;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((objId == null) ? 0 : objId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			VWMLObjWrap other = (VWMLObjWrap) obj;
			if (objId == null) {
				if (other.objId != null) {
					return false;
				}
			} else if (!objId.equals(other.objId)) {
				return false;
			}
			return true;
		}
	}
	
	/**
	 * Describes type of association between two entities (the type can be link or association)
	 * @author ogibayev
	 *
	 */
	public static class VWMLLinkWrap {
		private Object id;
		private Object linkedId;
		private boolean asTerm = false;
		private boolean asLifeTerm = false;
		private boolean asSource = false;
		private String uniqId = "VWMLLINK_" + UUID.randomUUID().toString();
		private String[] contextPath = null; // associates with linkedId (its concrete context)
		private String activeContext = null; // active context - CE's context (aka linked)
		private String linkObjContext = null; // (aka linking)
		
		public VWMLLinkWrap(Object id, Object linkedId) {
			super();
			this.id = id;
			this.linkedId = linkedId;
			parseContextPath();
		}

		public VWMLLinkWrap(Object id, Object linkedId, boolean asTerm) {
			super();
			this.id = id;
			this.linkedId = linkedId;
			this.asTerm = asTerm;
			parseContextPath();
		}

		public VWMLLinkWrap(Object id, Object linkedId, boolean asTerm, boolean asLifeTerm) {
			super();
			this.id = id;
			this.linkedId = linkedId;
			this.asTerm = asTerm;
			this.asLifeTerm = asLifeTerm;
			parseContextPath();
		}

		public VWMLLinkWrap(Object id, Object linkedId, boolean asTerm, boolean asLifeTerm, boolean asSource) {
			super();
			this.id = id;
			this.linkedId = linkedId;
			this.asTerm = asTerm;
			this.asLifeTerm = asLifeTerm;
			this.asSource = asSource;
			parseContextPath();
		}
		
		public Object getId() {
			return id;
		}

		public void setId(Object id) {
			this.id = id;
		}

		public Object getLinkedId() {
			return linkedId;
		}
		
		public void setLinkedId(Object linkedId) {
			this.linkedId = linkedId;
		}

		public void setUniqId(String uniqId) {
			this.uniqId = uniqId;
		}

		public boolean isAsTerm() {
			return asTerm;
		}

		public void setAsTerm(boolean asTerm) {
			this.asTerm = asTerm;
		}

		public boolean isAsLifeTerm() {
			return asLifeTerm;
		}

		public void setAsLifeTerm(boolean asLifeTerm) {
			this.asLifeTerm = asLifeTerm;
		}

		public boolean isAsSource() {
			return asSource;
		}

		public void setAsSource(boolean asSource) {
			this.asSource = asSource;
		}

		public String getUniqId() {
			return uniqId;
		}

		public String[] getContextPath() {
			return contextPath;
		}
		
		public String getActiveContext() {
			return activeContext;
		}

		public void setActiveContext(String activeContext) {
			this.activeContext = activeContext;
		}

		public String getLinkObjContext() {
			return linkObjContext;
		}

		public void setLinkObjContext(String linkObjContext) {
			this.linkObjContext = linkObjContext;
		}

		@Override
		public String toString() {
			return "VWMLLinkWrap [id=" + id + ", linkedId=" + linkedId
					+ ", asTerm=" + asTerm + ", asLifeTerm=" + asLifeTerm
					+ ", uniqId=" + uniqId + ", contextPath="
					+ Arrays.toString(contextPath) + ", activeContext="
					+ activeContext + "]";
		}
		
		protected void parseContextPath() {
			String[] contextPath =  VWMLJavaExportUtils.parseContext((String)linkedId);
			if (contextPath != null && contextPath.length > 1) {
				this.contextPath = contextPath;
			}
		}
	}
	
	/**
	 * Links list of operations with entity, actually creates 'term'
	 * @author ogibayev
	 *
	 */
	public static class VWMLOperationLink {
		private Object entityId;
		private Object linkId;
		private List<String> associatedOperations;
		private EntityWalker.REL rel = EntityWalker.REL.NONE;
		
		public VWMLOperationLink() {
			super();
		}

		public VWMLOperationLink(Object entityId, Object linkId, List<String> associatedOperations, EntityWalker.REL rel) {
			super();
			this.entityId = entityId;
			this.linkId = linkId;
			this.associatedOperations = associatedOperations;
			this.rel = rel;
		}

		public Object getEntityId() {
			return entityId;
		}

		public void setEntityId(Object entityId) {
			this.entityId = entityId;
		}

		public Object getLinkId() {
			return linkId;
		}

		public EntityWalker.REL getRel() {
			return rel;
		}

		public void setLinkId(Object linkId) {
			this.linkId = linkId;
		}

		public List<String> getAssociatedOperations() {
			return associatedOperations;
		}

		public void setAssociatedOperations(List<String> associatedOperations) {
			this.associatedOperations = associatedOperations;
		}
	}
	
	public static class VWMLObjectTranslationElement {
		private Object id;
		private Object toId;
		private String[] contexts;
		
		public VWMLObjectTranslationElement(Object id, Object toId, String[] contexts) {
			super();
			this.id = id;
			this.toId = toId;
			this.contexts = contexts;
		}

		public Object getId() {
			return id;
		}

		public Object getToId() {
			return toId;
		}

		public String[] getContexts() {
			return contexts;
		}
	}
	
	// set of writers, for each file type
	private FileWriter fws[] = new FileWriter[ModuleFiles.numValues()];
	// used for debug purposes in order to visualize objects' linkage
	private AbstractVWMLLinkVisitor visitor = null;
	// declared VWML objects (simple entity, complex entity and terms)
	private List<VWMLObjWrap> declaredObjects = new ArrayList<VWMLObjWrap>();
	// declared contexts
	private List<VWMLObjWrap> declaredContexts = new ArrayList<VWMLObjWrap>();
	// declared creatures
	private List<VWMLObjWrap> declaredCreatures = new ArrayList<VWMLObjWrap>();
	// contains list of entity ids which were marked as 'term'
	private List<Object> markedAsTerm = new ArrayList<Object>();
	// defines objects' linkage
	private List<VWMLLinkWrap> linkage = new ArrayList<VWMLLinkWrap>();
	// defines objects' interpretation linkage (special type of linkage, one object is interpreted as second)
	private List<VWMLLinkWrap> interpret = new ArrayList<VWMLLinkWrap>();
	// associates object and operations
	private Map<Object, VWMLOperationLink> operations = new HashMap<Object, VWMLOperationLink>();
	// objects Id translation
	private List<VWMLObjectTranslationElement> idTranslationMap = new ArrayList<VWMLObjectTranslationElement>();
	// entities of the conflict ring
	private Map<String, List<String>> entitiesOfConflictRing = new HashMap<String, List<String>>();
	// currently processed conflict definition name
	private String activeConflictDefinitionName = null;
	private VWMLLinkWrap lastLink = null;
	// we need to store the last declared complex entity in order to detect complex context
	// if complex context is detected then this entity is removed from declaration storage and
	// becomes context
	private VWMLObjWrap lastDeclaredComplexEntity = null; 
	
	private Logger logger = Logger.getLogger(JavaCodeGenerator.class);
	
	public static JavaCodeGenerator instance() {
		return new JavaCodeGenerator();
	}
	
	public Object getLastLink() {
		return lastLink;
	}

	public Object getLastLinksUniqId() {
		Object o = null;
		if (lastLink != null) {
			o = lastLink.getUniqId();
		}
		return o;
	}
	
	/**
	 * Builds module's properties instance
	 * @return
	 */
	public StartModuleProps buildProps() {
		JavaModuleStartProps props = new JavaModuleStartProps();
		props.setCodeGenerator(this);
		return props;
	}
	
	/**
	 * Normalizes properties by setting non-set values by project properties values
	 * @param props
	 * @param projectProps
	 * @return
	 */
	public StartModuleProps normalizeProps(StartModuleProps props, StartModuleProps projectProps) {
		StartModuleProps normalizedProps = props;
		if (normalizedProps == null) {
			normalizedProps = projectProps;
		}
		else {
			JavaModuleStartProps javaModNormalizedProps = (JavaModuleStartProps)normalizedProps;
			JavaModuleStartProps javaProjProps = (JavaModuleStartProps)projectProps;
			if (javaModNormalizedProps.getAuthor() == null) {
				javaModNormalizedProps.setAuthor(javaProjProps.getAuthor());
			}
			if (javaModNormalizedProps.getDescription() == null) {
				javaModNormalizedProps.setDescription(javaProjProps.getDescription());
			}
			if (javaModNormalizedProps.getDate() == null) {
				javaModNormalizedProps.setDate(javaProjProps.getDate());
			}
			if (javaModNormalizedProps.getSrcPath() == null) {
				javaModNormalizedProps.setSrcPath(javaProjProps.getSrcPath());
			}
			if (javaModNormalizedProps.getCommonPackage() == null) {
				javaModNormalizedProps.setCommonPackage(javaProjProps.getCommonPackage());
			}
			if (javaModNormalizedProps.getModulePackage() == null) {
				javaModNormalizedProps.setModulePackage(javaProjProps.getModulePackage());
			}
			if (javaModNormalizedProps.getVisitor() == null) {
				javaModNormalizedProps.setVisitor(javaProjProps.getVisitor());
			}
			if (javaModNormalizedProps.getVisitorDataPath() == null) {
				javaModNormalizedProps.setVisitorDataPath(javaProjProps.getVisitorDataPath());
			}
			if (javaModNormalizedProps.getProjectName() == null) {
				javaModNormalizedProps.setProjectName(javaProjProps.getProjectName());
			}
			if (javaModNormalizedProps.getInterpretationProps() == null) {
				javaModNormalizedProps.setInterpretationProps(javaProjProps.getInterpretationProps());
			}
			if (javaModNormalizedProps.getEntityHistorySize() == null) {
				javaModNormalizedProps.setEntityHistorySize(javaProjProps.getEntityHistorySize());
			}
		}
		return normalizedProps;
	}
	
	/**
	 * Returns source's path
	 * @param props
	 * @return
	 */
	public String getSourcePath(StartModuleProps props) {
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;
		if (StartModuleProps.getSourcesPath() == null) {
			StartModuleProps.setSourcesPath(modProps.getSrcPath());
		}
		return modProps.getSrcPath() + "/" + modProps.getModulePackage().replaceAll("\\.", "/");
	}
	
	/**
	 * Called by VWMLProcessor when new software module is generated
	 * @param props
	 */
	public void startModule(StartModuleProps props) throws Exception {
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;
		String[] startLines = {
			// 1. caption
			JavaCodeGeneratorUtils.prepareCaption(modProps) + "\r\n\r\n",
			// 2. package
			"package " + modProps.getModulePackage() + ";\r\n\r\n",
			// 3. imports
			prepareImports()
		};
		String moduleFileName = JavaCodeGeneratorUtils.capitalizeFirstLetter(modProps.getModuleName());
		// files related to module => 
		// {module itself, repository (where objects are created), linkage (where objects are linked)}
		String[] modFiles = {
			JavaCodeGeneratorUtils.generateClassName(ModuleFiles.MODULE.toValue() + moduleFileName) + ".java",
			JavaCodeGeneratorUtils.generateClassName(ModuleFiles.REPOSITORY.toValue() +  moduleFileName) + ".java",
			JavaCodeGeneratorUtils.generateClassName(ModuleFiles.LINKAGE.toValue() + moduleFileName) + ".java",
		};
		// stores actual module's name inside the properties for further processing
		modProps.setActualModuleName(JavaCodeGeneratorUtils.generateClassName(ModuleFiles.MODULE.toValue() + moduleFileName));
		String moduleFullPath = getSourcePath(modProps);
		File f = new File(moduleFullPath);
		if (!f.exists() && !f.mkdirs()) {
			throw new Exception("Couldn't create path '" + moduleFullPath + "'");
		}
		// creates files and adds caption, package name and imports
		for(int i = 0; i < fws.length; i++) {
			fws[i] = new FileWriter(moduleFullPath + "/" + modFiles[i]);
			for(String line : startLines) {
				fws[i].write(line);
			}
		}
		if (modProps.getVisitor() != null && getVisitor() == null) {
			setVisitor(modProps.getVisitor());
		}
		if (logger.isInfoEnabled()) {
			logger.info("The module '" + modProps.getModuleName() + "' is being built");
		}
	}

	/**
	 * Actually generates module's code
	 * @param modProps
	 */
	public void generate(StartModuleProps props) throws Exception {
		normalizeCode();
		JavaModuleStartProps modProps = (JavaModuleStartProps)props;		
		new JavaCodeGeneratorModule(fws[ModuleFiles.index(ModuleFiles.MODULE.toValue())]).buildModuleBody(modProps, getVisitor());
		new JavaCodeGeneratorRepository(fws[ModuleFiles.index(ModuleFiles.REPOSITORY.toValue())]).buildModuleRepositoryPart(modProps, declaredObjects, declaredCreatures, declaredContexts, entitiesOfConflictRing);
		new JavaCodeGeneratorLinkage(fws[ModuleFiles.index(ModuleFiles.LINKAGE.toValue())]).buildModuleLinkagePart(modProps, linkage, interpret, markedAsTerm, operations);
	}
	
	/**
	 * Finishes generation of module
	 * @param props
	 * @throws Exception
	 */
	public void finishModule(StartModuleProps props) throws Exception {
		// finalizes builder and linkage
		// finalizes module
		FileWriter fw = fws[ModuleFiles.index(ModuleFiles.MODULE.toValue())];
		fw.write(JavaCodeGeneratorUtils.getS_classEndDef());
		for(int i = 0; i < fws.length; i++) {
			fws[i].flush();
			fws[i].close();
		}		
		declaredObjects.clear();
		operations.clear();
		linkage.clear();
		declaredContexts.clear();
		declaredCreatures.clear();
		interpret.clear();
		markedAsTerm.clear();
		idTranslationMap.clear();
		if (logger.isInfoEnabled()) {
			logger.info("The module '" + ((JavaModuleStartProps)props).getModuleName() + "' was built");
		}
	}	
	
	/**
	 * Marks entity as term
	 * @param id (REL)
	 * @param contexts
	 * @throws Exception
	 */
	public void markEntityAsTerm(Object id, String[] contexts) throws Exception {
		EntityWalker.Relation rel = (EntityWalker.Relation)id;
		markedAsTerm.add(rel.getObj());
		Iterator<VWMLLinkWrap> it = null;
		if (rel.getRelation() == REL.ASSOCIATION) {
			it = interpret.iterator();
		}
		else {
			it = linkage.iterator();
		}
		while(it.hasNext()) {
			VWMLLinkWrap lw = it.next();
			if (lw.getLinkedId().equals(rel.getObj()) && rel.getLastLink() != null && lw.getUniqId().equals(((VWMLLinkWrap)rel.getLastLink()).getUniqId())) {
				for(String context : contexts) {
					if (logger.isDebugEnabled()) {
						logger.debug("entity '" + rel.getObj() + "' candidate to term on context '" + context + "'; active context '" + lw.getActiveContext() + "'");
					}
					if (context.equals(lw.getActiveContext())) {
						lw.setAsTerm(true);
					}
				}
			}
		}
	}

	/**
	 * Marks entity as lifeterm; the entity had to added and marked as term before this method is called
	 * @param id (REL)
	 * @param asSource lifeterm is considered as source term
	 * @throws Exception
	 */
	public void markEntityAsLifeTerm(Object id, boolean asSource) throws Exception {
		boolean found = false;
		EntityWalker.Relation rel = (EntityWalker.Relation)id;
		// looking for the term which was added before
		for(Object objId : markedAsTerm) {
			if (objId == rel.getObj()) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new Exception("The term identified by '" + id + "' should be added and marked as term before markEntityAsLifeTerm is called");
		}
		if (rel.getLastLink() != null && ((VWMLLinkWrap)rel.getLastLink()).isAsTerm()) {
			((VWMLLinkWrap)rel.getLastLink()).setAsLifeTerm(true);
			((VWMLLinkWrap)rel.getLastLink()).setAsSource(asSource);
		}
		else {
			throw new Exception("Inconsistency found; object identified by '" + id + "' found in markedAsTerm collection but it is not marked as term");
		}
	}
	
	/**
	 * Not implemented for VWML
	 */
	public void markEntityAsLifeTermOnContexts(Object id, boolean asSource, String[] contexts) throws Exception {
		throw new Exception("not implemented for VWML");
	}
	
	/**
	 * Declares simple entity
	 * @param id (ID)
	 * @param context
	 * @throws Exception
	 */
	public void declareSimpleEntity(Object id, String context) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id, context));
	}
	
	/**
	 * Declares complex entity; the object id is compound object; consists from set of simple entity ids
	 * @param id (ID)
	 * @param readableId (ID)
	 * @param context
	 * @throws Exception
	 */
	public void declareComplexEntity(Object id, Object readableId, String context) throws Exception {
		lastDeclaredComplexEntity = new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.COMPLEX_ENTITY, id, readableId, context);
		declaredObjects.add(lastDeclaredComplexEntity);		
	}

	/**
	 * Declares creature; special entity which lives on intersection of worlds
	 * @param id (ID)
	 * @param props (STRING)
	 * @param context
	 * @throws Exception
	 */
	public void declareCreature(Object id, Object props, String context) throws Exception {
		declaredCreatures.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.CREATURE, id, props, context));		
	}
	
	/**
	 * Declares term
	 * @param id (ID)
	 * @param context
	 * @throws Exception
	 */
	public void declareTerm(Object id, String context) throws Exception {
		declaredObjects.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.TERM, id, context));
	}

	/**
	 * Declares context; it is used during code generation phase; all entities should be linked with contexts
	 * @param contextId
	 */
	public void declareContext(Object contextId) {
		declaredContexts.add(new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.CONTEXT, contextId, null));
	}
	
	/**
	 * Links context and bunch to which context belongs
	 * @param contextId
	 * @param bunch
	 * @param bunch
	 */
	public void linkContextAndBunch(Object contextId, Object bunch) {
		
	}
	
	/**
	 * Removes last declared complex entity; complex context detected
	 * @param id (REL)
	 */
	public boolean removeComplexEntityFromDeclarationAndLinkage(Object id, String[] contexts) {
		EntityWalker.Relation rel = (EntityWalker.Relation)id;
		removeFrom(linkage, rel, contexts);
		removeFrom(interpret, rel, contexts);
		Iterator<VWMLObjWrap> it = declaredObjects.iterator();
		while(it.hasNext()) {
			VWMLObjWrap w = it.next();
			if (w.getObjId().equals(rel.getObj())) {
				for(String context : contexts) {
					if (context.equals(w.getContext())) {
						System.out.println("removed '" + w.getObjId() + "' on context '" + context + "'");
						it.remove();
					}
				}
				break;
			}
		}
		
		return true;
	}
	
	/**
	 * Changes object's id from 'id' to 'idTo'
	 * @param id
	 * @param idTo
	 * @param contexts
	 */
	public void changeObjectIdTo(Object id, Object idTo, String[] contexts) {
		idTranslationMap.add(new VWMLObjectTranslationElement(id, idTo, contexts));
	}

	/**
	 * Changes object's id from 'id' to 'idTo'
	 * @param id
	 * @param idTo
	 * @param contexts
	 */
	public void changeObjectIdToImmidiatly(Object id, Object idTo, String[] contexts) {
		changeObjectIdToForDeclaredObjectsOnly(id, idTo, contexts);
		changeObjectIdToIn(id, idTo, linkage, contexts);
		changeObjectIdToIn(id, idTo, interpret, contexts);
	}
	
	/**
	 * Changes object's id for declared objects only
	 * @param id
	 * @param idTo
	 */
	public void changeObjectIdToForDeclaredObjectsOnly(Object id, Object idTo, String[] contexts) {
		for(VWMLObjWrap o : declaredObjects) {
			if (o.getObjId().equals(id)) {
				for(String context : contexts) {
					if (context.equals(o.getContext())) {
						System.out.println("changed '" + o.getObjId() + "' on context '" + context + "'; to '" + idTo + "'");
						o.setObjId(idTo);
					}
				}
			}
		}		
	}
	
	/**
	 * Links objects using their ids
	 * @param id (ID)
	 * @param linkedObjId
	 * @param linkingContext
	 * @param activeContext
	 * @param uniqId
	 */
	public void linkObjects(Object id, Object linkedObjId, String linkingContext, String activeContext, Object uniqId) {
		lastLink = new VWMLLinkWrap(id, linkedObjId);
		lastLink.setActiveContext(activeContext);
		lastLink.setLinkObjContext(linkingContext);
		if (uniqId != null) {
			lastLink.setUniqId((String)uniqId);
		}
		linkage.add(lastLink);
	}
	
	/**
	 * Builds association between object and operation
	 * @param id (REL)
	 * @param op
	 * @param activeContext
	 */
	public void associateOperation(Object id, String op, String activeContext) {
		EntityWalker.Relation rel = (EntityWalker.Relation)id;
		if (rel == null || rel.getLastLink() == null) {
			logger.error("couldn't associated operation '" + op + "' since last linkage operation is absent");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Object '" + id + "' -> op '" + op + "'");
		}
		String uniqId = ((VWMLLinkWrap)rel.getLastLink()).getUniqId();
		VWMLOperationLink associatedOps = operations.get(uniqId);
		if (associatedOps == null) {
			associatedOps = new VWMLOperationLink(rel.getObj(), uniqId, new ArrayList<String>(), rel.getRelation());
			operations.put(uniqId, associatedOps);
		}
		associatedOps.getAssociatedOperations().add(op);
	}
	
	/**
	 * Set interpreting link between objects
	 * @param id (ID)
	 * @param interpretingObjId
	 * @param linkingContext
	 * @param activeContext
	 */
	public void interpretObjects(Object id, Object interpretingObjId, String linkingContext, String activeContext) {
		lastLink = new VWMLLinkWrap(id, interpretingObjId);
		lastLink.setActiveContext(activeContext);
		lastLink.setLinkObjContext(linkingContext);
		interpret.add(lastLink);
	}
	
	/**
	 * Starts definition of conflict on the ring
	 * @param conflictDefinitionName
	 */
	public void startConflictDefinitionOnRing(String conflictDefinitionName) throws Exception {
		String rawConflictDefinitionName = parseBoundTerm2ConflictAssociation(conflictDefinitionName);
		String context = stripContextFrom(rawConflictDefinitionName);
		if (!checkIfSimpleEntityDeclared(rawConflictDefinitionName, context)) {
			declareSimpleEntity(rawConflictDefinitionName, context);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("conflict definition on ring '" + rawConflictDefinitionName + "' defined on context '" + context + "'");
		}
		if (entitiesOfConflictRing.get(conflictDefinitionName) == null) {
			entitiesOfConflictRing.put(conflictDefinitionName, new ArrayList<String>());
		}
		activeConflictDefinitionName = conflictDefinitionName;
	}
	
	/**
	 * Adds definition of conflict, on the ring, to the ring
	 * @param conflictDefinitionName
	 */
	public void addConflictDefinitionOnRing(String conflictDefinitionName) throws Exception {
		if (activeConflictDefinitionName == null) {
			throw new Exception("active conflict definition name is null");
		}
		List<String> conflicts = entitiesOfConflictRing.get(activeConflictDefinitionName);
		if (conflicts == null) {
			throw new Exception("trying to add conflict definition outside conflict section");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("connects conflicts '" + activeConflictDefinitionName + "' and '" + conflictDefinitionName + "'");
		}
		if (!conflicts.contains(conflictDefinitionName)) {
			conflicts.add(conflictDefinitionName);
		}
		String rawConflictDefinitionName = parseBoundTerm2ConflictAssociation(conflictDefinitionName);
		String context = stripContextFrom(rawConflictDefinitionName);
		if (!checkIfSimpleEntityDeclared(rawConflictDefinitionName, context)) {
			declareSimpleEntity(rawConflictDefinitionName, context);
		}
		// reversing include...
		List<String> reversingConflicts = entitiesOfConflictRing.get(conflictDefinitionName);
		if (reversingConflicts == null) {
			reversingConflicts = new ArrayList<String>();
			entitiesOfConflictRing.put(conflictDefinitionName, reversingConflicts);
		}
		if (!reversingConflicts.contains(activeConflictDefinitionName)) {
			reversingConflicts.add(activeConflictDefinitionName);
		}
	}
	
	/**
	 * Ends definition of conflict on the ring
	 * @param conflictDefinitionName
	 */
	public void endConflictDefinitionOnRing() throws Exception {
		activeConflictDefinitionName = null;
	}

	public AbstractVWMLLinkVisitor getVisitor() {
		return visitor;
	}

	public void setVisitor(AbstractVWMLLinkVisitor visitor) {
		this.visitor = visitor;
	}

	/**
	 * Returns language's name as string
	 * @return
	 */
	public String getLangAsString() {
		return "Java";
	}	

	protected void normalizeCode() {
		for(VWMLObjectTranslationElement t : idTranslationMap) {
			changeObjectIdToForDeclaredObjectsOnly(t.getId(), t.getToId(), t.getContexts());
			changeObjectIdToIn(t.getId(), t.getToId(), linkage, t.getContexts());
			changeObjectIdToIn(t.getId(), t.getToId(), interpret, t.getContexts());
		}
	}
	
	private String prepareImports() {
		Class<?> importedClasses[] = {};
		String imports = new String();
		for(Class<?> ic : importedClasses) {
			imports += "import " + ic.getName() + ";\r\n";
		}
		return imports;
	}
	
	private void changeObjectIdToIn(Object id, Object idTo, List<VWMLLinkWrap> link, String[] contexts) {
		for(VWMLLinkWrap w : link) {
			if (w.getId().equals(id)) {
				for(String context : contexts) {
					if (context.equals(w.getLinkObjContext())) {
						System.out.println("changed id '" + id + "' to '" + idTo + "' on context '" + context + "'");
						w.setId(idTo);
					}
				}
			}
			if (w.getLinkedId().equals(id)) {
				for(String context : contexts) {
					if (context.equals(w.getActiveContext())) {
						System.out.println("changed id '" + id + "' to '" + idTo + "' on context '" + context + "'");
						w.setLinkedId(idTo);
					}
				}
			}
		}
	}
	
	private void removeFrom(List<VWMLLinkWrap> from, EntityWalker.Relation rel, String[] contexts) {
		Iterator<VWMLLinkWrap> it = from.iterator();
		while(it.hasNext()) {
			VWMLLinkWrap lw = it.next();
			if (lw.getId().equals(rel.getObj())) {
				for(String context : contexts) {
					if (context.equals(lw.getLinkObjContext())) {
						it.remove();
					}
				}
			}
			else
			if (lw.getLinkedId().equals(rel.getObj())) {
				for(String context : contexts) {
					if (context.equals(lw.getActiveContext())) {
						it.remove();
					}
				}
			}
		}
	}
	
	private String stripContextFrom(String entity) {
		if (entity.lastIndexOf('.') != -1) {
			return entity.substring(0, entity.lastIndexOf('.'));
		}
		return "";
	}
	
	private boolean checkIfSimpleEntityDeclared(Object id, String context) {
		VWMLObjWrap v = new VWMLObjWrap(VWMLObjectBuilder.VWMLObjectType.SIMPLE_ENTITY, id, context);
		return declaredObjects.contains(v);
	}	
	
	private String parseBoundTerm2ConflictAssociation(String conflictDefinitionName) {
		String parsedAssociation = conflictDefinitionName;
		String boundTerm = GeneralUtils.getConflictBoundTerm(conflictDefinitionName);
		if (boundTerm != null) {
			String suffix = conflictDefinitionName.substring(conflictDefinitionName.lastIndexOf("}") + 1);
			parsedAssociation = boundTerm + suffix;
		}
		return parsedAssociation;
	}
}
